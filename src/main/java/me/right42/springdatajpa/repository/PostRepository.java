package me.right42.springdatajpa.repository;

import me.right42.springdatajpa.domain.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends MyRepository<Post, Long>, PostCustomRepository {

    List<Post> findByTitleStartsWith(String title);

    List<Post> findByTitleWithNamedQuery(String title);

    @Query("SELECT p from Post p WHERE p.title = :title")
    List<Post> findByTitleWithQuery(String title);

    @Query("SELECT p from Post p WHERE p.title = :title")
    List<Post> findByTitleWithQuery(String title, Sort sort);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post p set p.title = :title where p.id = :id")
    int updateTitle(String title, Long id);
}
