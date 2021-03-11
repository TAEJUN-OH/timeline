package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.domain.PostStatus;
import com.example.timelineservice.timeline.repository.PostRepositoryOrigin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired EntityManager em;
    @Autowired PostService postService;
    @Autowired
    PostRepositoryOrigin postRepositoryOrigin;

    @Test
    public void 포스팅() throws Exception {

        //given
        Member member = createMember();
        String contents = "오늘 하루도 힘내세요!";

        //when
        Long postId = postService.post(member.getId(), contents);

        //then
        Post getPost = postRepositoryOrigin.findOne(postId);
        assertEquals("포스팅 시 포스트 상태는 POST", PostStatus.POST, getPost.getStatus());
    }


    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        em.persist(member);
        return member;
    }
}
