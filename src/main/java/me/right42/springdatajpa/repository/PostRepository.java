package me.right42.springdatajpa.repository;

import me.right42.springdatajpa.domain.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends MyRepository<Post, Long>, PostCustomRepository {

    List<Post> findByTitleStartsWith(String title);

    List<Post> findByTitleWithNamedQuery(String title);

    @Query("SELECT p from Post p WHERE p.title = :title")
    List<Post> findByTitleWithQuery(String title);

}
