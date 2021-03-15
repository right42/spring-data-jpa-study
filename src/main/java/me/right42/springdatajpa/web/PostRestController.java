package me.right42.springdatajpa.web;

import lombok.RequiredArgsConstructor;
import me.right42.springdatajpa.domain.Post;
import me.right42.springdatajpa.dto.PostSearch;
import me.right42.springdatajpa.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostRepository postRepository;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }

    @GetMapping("/posts")
    public PagedModel<EntityModel<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> postPagedResourcesAssembler){
        return postPagedResourcesAssembler.toModel(postRepository.findAll(pageable));
    }

    @GetMapping("/posts/test")
    public List<Post> getPosts(@ModelAttribute PostSearch postSearch) {
        return postRepository.findByTitle(postSearch.getTitle());
    }

}
