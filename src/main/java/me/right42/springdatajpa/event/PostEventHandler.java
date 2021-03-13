package me.right42.springdatajpa.event;

import org.springframework.context.event.EventListener;

public class PostEventHandler {

    @EventListener
    public void postEventHandle(PostPublishedEvent event) {

        System.out.println("============= event publish ======");
        System.out.println(event.getPost().getTitle());
        System.out.println("============= event publish ======");

    }
}
