package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Follow;
import com.example.timelineservice.timeline.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow , Long> {

    /**
     * 팔로워 조회
     * @param followMember
     * @return FollowList
     */
    List<Follow> findByFollowMember(Member followMember);


    /**
     * 팔로잉 회원 조회
     * @param member
     * @return FollowList
     */
    List<Follow> findByMember(Member member);


    /**
     * 팔로우 조회
     * @param memberId
     * @param followMemberId
     * @return Follow
     */
    Follow findByMemberIdAndFollowMemberId(Long memberId, Long followMemberId);

}
