package me.right42.springdatajpa.repository;

import lombok.RequiredArgsConstructor;
import me.right42.springdatajpa.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryDefault implements PostCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        return entityManager.createQuery(
                "select p from Post p", Post.class
            )
            .getResultList();
    }
}
