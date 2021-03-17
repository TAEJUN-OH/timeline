package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.domain.Post;
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
     * @param memberId
     * @param content
     * @return postId
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
     * @param postId
     * @return Post
     */
    @Transactional(readOnly = true)
    public Post findOne(Long postId){
        Post post = postRepository.findById(postId).get();
        return post;
    }


    /**
     * 내 포스트 조회 (10개씩) paging 추가예정
     * @param memberId
     * @return PostList
     */
    @Transactional(readOnly = true)
    public List<Post> findByPosts(Long memberId){
        List<Post> posts = postRepository.findAllByIdOrderByCreatedAtDesc(memberId);
        return posts;
    }


    /**
     * 뉴스 피드 (10개씩) paging 추가예정
     * @param memberId
     * @return NewsFeed
     */
    @Transactional(readOnly = true)
    public List<Post> findByNewsFeed(Long memberId) {
        List<Post> findNewsFeed = postRepository.findByNewsFeed(memberId);

        return findNewsFeed;
    }

    /**
     * 포스트 수정
     * @param id
     * @param content
     */
    @Transactional
    public void update(Long id, String content) {
        Post post = postRepository.findById(id).get();
        post.setContent(content);
    }

    /**
     * 포스트 삭제
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
