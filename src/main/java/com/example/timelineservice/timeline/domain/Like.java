package com.example.timelineservice.timeline.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "likes")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime createdAt;

    //==연관관계 메서드==//
    public void setPost(Post post) {
        this.post = post;
        post.getLikes().add(this);
    }


    //== Like 생성 메서드==/
    public static Like createLike(Member member , Post post) {
        Like like = new Like();
        like.setMember(member);
        like.setPost(post);
        like.setCreatedAt(LocalDateTime.now());
        return like;
    }
}
