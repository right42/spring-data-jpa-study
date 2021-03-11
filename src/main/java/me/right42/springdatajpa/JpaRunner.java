package me.right42.springdatajpa;

import me.right42.springdatajpa.domain.Comment;
import me.right42.springdatajpa.domain.Post;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class JpaRunner implements ApplicationRunner {

    private final EntityManager em;

    public JpaRunner(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("post1");

        Comment comment = new Comment();
        comment.setComment("comment");

        Comment comment2 = new Comment();
        comment2.setComment("comment2");

        post.addComment(comment);
        post.addComment(comment2);

        em.persist(post);
    }
}

