package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
