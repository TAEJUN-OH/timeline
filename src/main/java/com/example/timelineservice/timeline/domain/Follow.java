package com.example.timelineservice.timeline.domain;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "follow")
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "follow_member_id")
    private Member followMember;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getFollower();
    }

    public void setFollowMember(Member followMember) {
        this.followMember = followMember;
        followMember.getFollowing();
    }


    //== 팔로우 생성 메서드 ==//
    public static Follow createFollow(Member member , Member followMember) {
        Follow follow = new Follow();
        follow.setMember(member);
        follow.setFollowMember(followMember);
        follow.setCreatedAt(LocalDateTime.now());
        follow.setUpdatedAt(LocalDateTime.now());
        return follow;
    }

}
