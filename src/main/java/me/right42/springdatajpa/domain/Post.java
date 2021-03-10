package me.right42.springdatajpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

}
