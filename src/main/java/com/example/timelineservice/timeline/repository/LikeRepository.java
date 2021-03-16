package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Boolean existsByPostIdAndMemberId(Long postId, Long memberId);

    List<Like> findAllById(Long postId);
}
