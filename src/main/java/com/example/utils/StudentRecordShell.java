package com.example.utils;

import com.example.event.EventHolder;
import com.example.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ShellComponent
@Component
@RequiredArgsConstructor
public class StudentRecordShell {

    @Value("${students.list}")
    private String studentsList;

    private final List<Student> studentsRecord;

    private final ApplicationEventPublisher publisher;

    @ShellMethod
    public void add(String firstName, String lastName, Integer age){
        int index = studentsRecord.size() > 0 ? studentsRecord.stream().map(Student::getId).reduce(Integer::max).get() : 0;
        Student student = new Student(index + 1, firstName, lastName, age);
        studentsRecord.add(student);
        
        publisher.publishEvent(new EventHolder(this, student, null));
        
        writeStudent(MessageFormat.format("{0};{1};{2}",
                firstName, lastName, age),
                true);
    }

    @ShellMethod
    public void print() {
        System.out.println("List of students:");
        if(studentsRecord.isEmpty())
            System.out.println("Empty");
        studentsRecord.forEach(Student::printContact);
    }

    @ShellMethod
    public void delete(Integer id) {
        if (!studentsRecord.stream().map(Student::getId).collect(Collectors.toList()).contains(id)) {
            System.out.println(MessageFormat.format("Student with id {0} doesn't exist.", id));
            return;
        }
        studentsRecord.removeIf(student -> {
            if(student.getId().equals(id)){
                writeStudent("", false);
                //System.out.println(MessageFormat.format("Student with id {0} was removed.", id));
                publisher.publishEvent(new EventHolder(this, null, id));
                return true;
            }
            return false;
        });
        studentsRecord.forEach(contact -> writeStudent(contact.toString(), true));
    }

    @ShellMethod
    public void clearRecords() {
        studentsRecord.clear();
        writeStudent("", false);
        System.out.println("Students list was totally cleared");
    }

    private void addInitialStudent(String studentString, Boolean append) {
        List<String> student = Arrays.asList(studentString.split(";"));

        add(student.get(0).trim(), student.get(1).trim(), Integer.valueOf(student.get(2).trim()));

        if (append)
            writeStudent(MessageFormat.format("{0};{1};{2}",
                    student.get(0).trim(), student.get(1).trim(), Integer.valueOf(student.get(2).trim())),
                    true);
    }

    private void writeStudent(String student, Boolean append){
        try(FileWriter writer = new FileWriter(studentsList, append)) {
            writer.write(student);
            if(append)
                writer.append("\n");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
