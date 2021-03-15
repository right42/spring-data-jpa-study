package me.right42.springdatajpa.web;

import lombok.RequiredArgsConstructor;
import me.right42.springdatajpa.domain.Post;
import me.right42.springdatajpa.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostRepository postRepository;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }
}
