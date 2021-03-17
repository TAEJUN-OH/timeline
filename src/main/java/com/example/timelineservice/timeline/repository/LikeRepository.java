package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    /**
     * 좋아요 등록여부
     * @param postId
     * @param memberId
     * @return boolean
     */
    Boolean existsByPostIdAndMemberId(Long postId, Long memberId);

    /**
     * 좋아요 조회
     * @param postId
     * @return LikeList
     */
    List<Like> findAllById(Long postId);
}
