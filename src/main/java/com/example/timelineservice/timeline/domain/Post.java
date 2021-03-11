package com.example.timelineservice.timeline.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "post")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String contents;

    private Integer likeCnt;

    private LocalDateTime postDate; //작성시간

    @Enumerated(EnumType.STRING)
    private PostStatus status; //포스팅 상태[POST, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    //==생성 메서드==/
    public static Post createPost(Member member , String contents) {
        Post post = new Post();
        post.setMember(member);
        post.setContents(contents);
        post.setPostDate(LocalDateTime.now());
        post.setStatus(PostStatus.POST);
        return post;
    }
}
