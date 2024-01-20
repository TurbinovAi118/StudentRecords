package com.example.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHolderListener {

    @EventListener
    public void listen(EventHolder eventHolder) {
        System.out.println("Call listen method");

        if(eventHolder.getStudent() != null)
            System.out.println(eventHolder.getStudent());
        if (eventHolder.getStudentId() != null)
            System.out.println(eventHolder.getStudentId());
    }

}
