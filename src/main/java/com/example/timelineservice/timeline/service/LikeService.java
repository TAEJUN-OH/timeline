package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Like;
import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final MemberService memberService;

    /**
     * 좋아요 등록
     */
    @Transactional
    public Long like(Long postId , Long memberId) {
        Member member = memberService.findOne(memberId);
        Post post = postService.findOne(postId);
        Like like = Like.createLike(member, post);
        likeRepository.save(like);
        return like.getId();
    }


    /**
     * 좋아요 취소
     */
    @Transactional
    public void cancel(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
