package me.right42.springdatajpa.repository;

import me.right42.springdatajpa.domain.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void findByIdTest(){
        Comment comment = new Comment();
        comment.setComment("test");

        commentRepository.save(comment);

        Optional<Comment> byId = commentRepository.findById(1L);
        assertThat(byId).isNotEmpty();
    }

}