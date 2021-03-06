package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    /**
     * 회원찾기
     * @param email
     * @return MemberList
     */
    List<Member> findByEmail(String email);
}
