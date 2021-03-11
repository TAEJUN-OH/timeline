package com.example.timelineservice.timeline.repository;

import com.example.timelineservice.timeline.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.email= :email")
    List<Member> findByEmail(@Param("email") String email);

    @Modifying
    @Query("update Member m set m.delYn = 'Y' where m.id = :member_id ")
    void deleteByMember(@Param("member_id") Long Id);
}
