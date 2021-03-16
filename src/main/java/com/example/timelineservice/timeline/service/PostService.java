package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Follow;
import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.repository.FollowRepository;
import com.example.timelineservice.timeline.repository.MemberRepository;
import com.example.timelineservice.timeline.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 포스팅
     */
    @Transactional
    public Long post(Long memberId , String content) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();

        //포스트 생성
        Post post = Post.createPost(member, content);

        //포스트 저장
        postRepository.save(post);

        return post.getId();
    }

    /**
     * 포스트 조회
     */
    public Post findOne(Long postId){
        Post post = postRepository.findById(postId).get();
        return post;
    }

    /**
     * 뉴스 피드
     */
    public List<Post> findByNewsFeed(Long memberId) {
        return postRepository.findByNewsFeed(memberId);
    }


    /**
     * 포스트 전체 조회
     */
    public List<Post> findAll() {
        return postRepository.findAll();
    }


    /**
     * 포스트 수정
     */
    @Transactional
    public void update(Long id, String content) {
        Post post = postRepository.findById(id).get();
        post.setContent(content);
    }

    /**
     * 포스트 삭제
     */
    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
