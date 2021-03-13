package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //스프링과 테스트 통합
@SpringBootTest //스프링부트 띄운상태에서 테스트(이게 없으면 @Autowired 다 실패)
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback
    public void 회원가입() throws  Exception {

        //given
        Member member = new Member();
        member.setName("Oh");
        member.setEmail("abcd@mail.com");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findById(savedId).get());
    }

    @Test(expected = IllegalStateException.class) //try catch 대신 excepted 키워드로 대체.
    public void 중복_회원_예외() throws  Exception {

        //given
        Member member1 = new Member();
        member1.setName("Oh");
        member1.setEmail("abc@email.com");

        Member member2 = new Member();
        member2.setName("Oh");
        member2.setEmail("abc@email.com");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");
    }
}