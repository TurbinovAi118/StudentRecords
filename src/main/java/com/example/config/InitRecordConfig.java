
package com.example.config;

import com.example.model.Student;
import com.example.utils.StudentRecordShell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Configuration
@ConditionalOnProperty("students.init.enabled")
public class InitRecordConfig {

    @Value("${students.init.list}")
    private String initListFile;

    @Bean
    public StudentRecordShell initShell(List<Student> students, ApplicationEventPublisher publisher){
        parseStudents(students);
        return new StudentRecordShell(students, publisher);
    }

    private void parseStudents(List<Student> students){
        int id = 1;
        try (Scanner scanner = new Scanner(new File(initListFile))) {
            while (scanner.hasNextLine()) {
                List<String> student = Arrays.asList(scanner.nextLine().split(";"));
                students.add(new Student(id, student.get(0).trim(), student.get(1).trim(), Integer.valueOf(student.get(2).trim())));
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

