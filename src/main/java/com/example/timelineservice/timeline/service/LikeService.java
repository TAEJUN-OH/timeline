package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Like;
import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final MemberService memberService;

    /**
     * 좋아요 등록
     * @param postId
     * @param memberId
     * @return likeId
     */
    @Transactional
    public void like(Long postId , Long memberId) {
        Boolean isLike = likeRepository.existsByPostIdAndMemberId(postId, memberId);
        if (isLike) {
            throw new IllegalStateException("이미 좋아요를 누르셨습니다");
        }

        Member member = memberService.findOne(memberId);
        Post post = postService.findOne(postId);
        Like like = Like.createLike(member, post);
        likeRepository.save(like);

        List<Like> likes = likeRepository.findAllById(postId);
        post.setLikeCnt(likes.size() + 1);
    }


    /**
     * 좋아요 취소
     * @param likeId
     * @param postId
     */
    @Transactional
    public void cancel(Long likeId , Long postId) {
        Post post = postService.findOne(postId);
        List<Like> likes = likeRepository.findAllById(postId);
        if (likes.size() > 0) {post.setLikeCnt(likes.size() - 1);}
        likeRepository.deleteById(likeId);
    }
}
