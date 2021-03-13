package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     *  회원가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    /**
     * 회원 전체조회
     */
    @Transactional(readOnly = true) //읽기에는 readOnly 를 쓴다.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }


    /**
     * 회원 수정
     */
    @Transactional
    public void update(Long id, String name , String email) { //변경감지
        Member member = memberRepository.findById(id).get();
        member.setName(name);
        member.setEmail(email);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void delete(Long memberId) {
        memberRepository.deleteByMember(memberId);
    }
}
