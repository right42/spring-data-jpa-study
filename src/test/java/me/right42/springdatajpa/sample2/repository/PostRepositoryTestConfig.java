package me.right42.springdatajpa.sample2.repository;

import me.right42.springdatajpa.event.PostEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    @Bean
    public PostEventHandler postPublishedEventListener(){
        return new PostEventHandler();
    }

}
