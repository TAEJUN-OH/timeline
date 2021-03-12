package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow , Long> {

    //팔로워
    @Query("select f from Follow f where f.followMember = :followMemberId")
    List<Follow> findByFollower(@Param("followMemberId") Long followMemberId);

    //팔로잉
    @Query("select f from Follow f where f.member = :memberId")
    List<Follow> findByFollowing(@Param("memberId") Long memberId);
}
