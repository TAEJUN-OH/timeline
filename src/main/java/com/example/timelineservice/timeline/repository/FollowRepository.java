package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Follow;
import com.example.timelineservice.timeline.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow , Long> {

    //팔로워
    List<Follow> findByFollowMember(Member followMember);

    //팔로잉
    List<Follow> findByMember(Member member);

    //팔로우
    Follow findByMemberIdAndFollowMemberId(Long memberId, Long followMemberId);

}
