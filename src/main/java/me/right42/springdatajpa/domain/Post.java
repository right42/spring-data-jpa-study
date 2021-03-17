package me.right42.springdatajpa.domain;

import lombok.Getter;
import lombok.Setter;
import me.right42.springdatajpa.event.PostPublishedEvent;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NamedQuery(name = "Post.findByTitleWithNamedQuery", query = "SELECT p from Post p WHERE p.title = :title")
@EntityListeners(AuditingEntityListener.class)
public class Post extends AbstractAggregateRoot<Post> {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @CreatedBy
    @ManyToOne(fetch = LAZY)
    private Account createUser;

    @LastModifiedBy
    @ManyToOne(fetch = LAZY)
    private Account updatedUser;

    // 도메인 이벤트 save trigger
    public Post publish(){
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }

    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedDate = LocalDateTime.now();
    }
}
