package me.right42.springdatajpa.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Study {

    @Id
    @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account owner;
}
