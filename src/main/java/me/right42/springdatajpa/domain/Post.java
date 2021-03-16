package me.right42.springdatajpa.domain;

import lombok.Getter;
import lombok.Setter;
import me.right42.springdatajpa.event.PostPublishedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NamedQuery(name = "Post.findByTitleWithNamedQuery", query = "SELECT p from Post p WHERE p.title = :title")
public class Post extends AbstractAggregateRoot<Post> {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    private LocalDateTime createdDate;

    // 도메인 이벤트 save trigger
    public Post publish(){
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }
}
