package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //뉴스피드 조회
    @Query("select p from Post p where p.member.id " +
            "in (select f.followMember.id from Follow f where f.member.id = :memberId) or p.member.id = :memberId order by p.updatedAt desc")
    List<Post> findByNewsFeed(@Param("memberId") Long memberId);
}
