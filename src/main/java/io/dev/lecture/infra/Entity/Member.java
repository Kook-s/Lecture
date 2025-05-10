package io.dev.lecture.infra.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;

    @OneToMany(mappedBy = "member")
    private List<Registration> Registration = new ArrayList<>();

}
