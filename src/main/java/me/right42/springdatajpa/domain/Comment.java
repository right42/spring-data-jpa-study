package me.right42.springdatajpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }
}
