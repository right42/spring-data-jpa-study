package me.right42.springdatajpa.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String userName;

    private String password;

    private LocalDateTime created;

    @OneToMany(mappedBy = "owner")
    private final List<Study> studies = new ArrayList<>();

    public void addStudy(Study study){
        studies.add(study);
        study.setOwner(this);
    }

    public void removeStudy(Study study) {
        studies.remove(study);
        study.setOwner(null);
    }

//    @Embedded
//    @AttributeOverride(name = "street", column = @Column(name = "home_street"))
//    @AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode"))
//    @AttributeOverride(name = "city", column = @Column(name = "home_city"))
//    @AttributeOverride(name = "state", column = @Column(name = "home_state"))
//    private Address homeAddress;
//
//    @Embedded
//    private Address officeAddress;

}
