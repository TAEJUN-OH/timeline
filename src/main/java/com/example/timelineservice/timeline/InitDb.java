package com.example.timelineservice.timeline;


import com.example.timelineservice.timeline.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = new Member();
            member.setName("userA");
            member.setEmail("aaa@gmail.com");
            member.setDelYn("N");

            Member member2 = new Member();
            member2.setName("userB");
            member2.setEmail("bbb@gmail.com");
            member2.setDelYn("N");

            Member member3 = new Member();
            member3.setName("userC");
            member3.setEmail("ccc@gmail.com");
            member3.setDelYn("N");

            Member member4 = new Member();
            member4.setName("userD");
            member4.setEmail("ddd@gmail.com");
            member4.setDelYn("N");

            em.persist(member);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
        }
    }

}
