package com.example.timelineservice.timeline.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memeber_id")
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    private String delYn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member")
    @JsonIgnoreProperties({"member","like"})
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnoreProperties({"member"})
    private List<Follow> follower = new ArrayList<>();

    @OneToMany(mappedBy = "followMember")
    @JsonIgnoreProperties({"followMember"})
    private List<Follow> following = new ArrayList<>();

    //==회원 생성 메서드==//
    public static Member createMember(String name , String email) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setDelYn("N");
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());
        return member;
    }
}
