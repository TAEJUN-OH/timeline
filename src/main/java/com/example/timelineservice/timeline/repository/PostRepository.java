package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
