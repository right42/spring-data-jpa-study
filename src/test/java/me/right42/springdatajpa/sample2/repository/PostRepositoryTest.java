package me.right42.springdatajpa.sample2.repository;

import me.right42.springdatajpa.domain.Post;
import me.right42.springdatajpa.event.PostPublishedEvent;
import me.right42.springdatajpa.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(PostRepositoryTestConfig.class)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    EntityManager entityManager;

    @Test
    void event(){
        Post post = new Post();
        post.setContent("test");
        post.setTitle("title");
        PostPublishedEvent event = new PostPublishedEvent(post);

        applicationContext.publishEvent(event);
    }

    @Test
    void crud(){
        Post post = new Post();
        post.setTitle("test");
        post.setContent("tttt");

        assertThat(postRepository.contains(post)).isFalse();

        postRepository.save(post.publish());

        assertThat(postRepository.contains(post)).isTrue();
    }

    @Test
    void saveTest() {
        Post post = new Post();
        post.setTitle("Jpa");

        Post savedPost = postRepository.save(post);

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(savedPost).isEqualTo(post);

        Post updatePost = new Post();
        updatePost.setId(post.getId());
        updatePost.setTitle("Hibernate");

        Post updatedPost = postRepository.save(updatePost);

        assertThat(updatedPost).isNotEqualTo(updatePost);
        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(entityManager.contains(updatePost)).isFalse();

        postRepository.findAll();
    }

    @Test
    void queryTest(){
        Post post = savePost();

        List<Post> jpa = postRepository.findByTitleStartsWith("jpa");
        assertThat(jpa).isNotEmpty();

        List<Post> namedQueryResult = postRepository.findByTitleWithNamedQuery(post.getTitle());
        assertThat(namedQueryResult).isNotEmpty();

        List<Post> queryResult = postRepository.findByTitleWithQuery(post.getTitle());
        assertThat(queryResult).isNotEmpty();
    }

    @Test
    void sortTest(){
        Post post = savePost();

        List<Post> posts = postRepository.findByTitleWithQuery(post.getTitle(), Sort.by("title"));

        assertThat(posts).isNotEmpty();

        List<Post> unsafeSortPosts = postRepository.findByTitleWithQuery(post.getTitle(), JpaSort.unsafe("LENGTH(title)"));

        assertThat(unsafeSortPosts).isNotEmpty();
    }


    @Test
    void updateTest(){
        Post post = savePost();

        postRepository.updateTitle("jpa1", post.getId());

        Optional<Post> byId = postRepository.findById(post.getId());
        assertThat(byId).isNotEmpty();
        assertThat(byId.get().getTitle()).isEqualTo("jpa1");
    }

    private Post savePost() {
        Post post = new Post();
        post.setTitle("jpa study");
        postRepository.save(post);
        return post;
    }
}