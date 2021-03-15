package me.right42.springdatajpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.right42.springdatajpa.domain.Post;
import me.right42.springdatajpa.domain.QPost;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static me.right42.springdatajpa.domain.QPost.*;

@Repository
public class PostCustomRepositoryDefault implements PostCustomRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory query;

    public PostCustomRepositoryDefault(EntityManager entityManager) {
        this.entityManager = entityManager;
        query = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Post> findMyPost() {
        return entityManager.createQuery(
                "select p from Post p", Post.class
            )
            .getResultList();
    }

    @Override
    public List<Post> findByTitle(String keyword) {
        QPost post = QPost.post;

        return query
                .selectFrom(post)
                .where(containsTitle(keyword))
                .fetch();
    }

    private BooleanExpression containsTitle(String keyword) {
        if(!StringUtils.hasText(keyword)) {
            return null;
        }

        return post.title.contains(keyword);
    }


}
