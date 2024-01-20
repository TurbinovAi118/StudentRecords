package com.example.model;

import lombok.*;

import java.text.MessageFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;

    public void printContact() {
        System.out.println(MessageFormat.format("Student №{0}. First name: {1}, last name: {2}, age: {3}.",
                id, firstName, lastName, age));
    }
}
