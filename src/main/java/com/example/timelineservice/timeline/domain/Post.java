package com.example.timelineservice.timeline.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "post")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

    private String content;

    private Integer likeCnt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    //==포스트 생성 메서드==//
    public static Post createPost(Member member , String content) {
        Post post = new Post();
        post.setMember(member);
        post.setContent(content);
        post.setLikeCnt(0);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return post;
    }
}
