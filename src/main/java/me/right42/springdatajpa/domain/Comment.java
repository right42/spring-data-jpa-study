package me.right42.springdatajpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedEntityGraph(name = "Comment.post",
    attributeNodes = @NamedAttributeNode("post")
)
@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
