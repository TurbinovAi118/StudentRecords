package com.example.event;

import com.example.model.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent{

    private final Student student;
    private final Integer studentId;

    public EventHolder(Object source, Student student, Integer studentId) {
        super(source);
        this.student = student;
        this.studentId = studentId;
    }
}
