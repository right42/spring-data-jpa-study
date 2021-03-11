package me.right42.springdatajpa.repository;

import me.right42.springdatajpa.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void initPost(){
        Post post = new Post();
        post.setTitle("spring data jpa");

        postRepository.save(post);
    }

    @Test
    void pageTest(){
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isZero();
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);
    }

    @Test
    void findAllTest(){
        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void findByTitleContains(){
        Page<Post> page = postRepository.findByTitleContains("spring", PageRequest.of(0, 10));

        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isZero();
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);
    }



}