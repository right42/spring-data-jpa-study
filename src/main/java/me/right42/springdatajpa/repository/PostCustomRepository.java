package me.right42.springdatajpa.repository;

import me.right42.springdatajpa.domain.Post;

import java.util.List;

public interface PostCustomRepository {

    List<Post> findMyPost();

    List<Post> findByTitle(String keyword);

}
