package me.right42.springdatajpa.repository;

import me.right42.springdatajpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
