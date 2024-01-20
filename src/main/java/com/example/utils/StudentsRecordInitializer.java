package com.example.utils;

import com.example.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


//@Component
//@ConditionalOnProperty("students.init.enabled")
public class StudentsRecordInitializer {

    /*
    private final StudentRecordShell shell;

    public StudentsRecordInitializer(StudentRecordShell shell) {
        this.shell = shell;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void initStudentRecord(){
        List<Student> list = new ArrayList<>();
        shell.setStudentsRecord(shell.parseStudents(list));
    }
    */
}
