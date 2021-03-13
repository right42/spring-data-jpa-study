package me.right42.springdatajpa.event;

import lombok.Getter;
import me.right42.springdatajpa.domain.Post;

@Getter
public class PostPublishedEvent {

    private final Post post;

    public PostPublishedEvent(Post post) {
        this.post = post;
    }
}
