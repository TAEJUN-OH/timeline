package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByEmail(String email);
}
