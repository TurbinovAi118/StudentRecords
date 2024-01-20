package com.example.config;

import com.example.model.Student;
import com.example.utils.StudentRecordShell;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
public class DefaultAppConfig {

    @Bean
    public StudentRecordShell defaultShell(List<Student> students, ApplicationEventPublisher publisher){
        return new StudentRecordShell(students, publisher);
    }

}
