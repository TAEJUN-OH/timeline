package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Like;
import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.service.LikeService;
import com.example.timelineservice.timeline.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final PostService postService;

    /**
     * 좋아요 등록 v1
     */
    @PostMapping("/api/v1/likes")
    public CreateLikeResponse create(@RequestBody @Valid CreateLikeRequest request) {
        Long likeId = likeService.like(request.getPostId(), request.getMemberId());
        return new CreateLikeResponse(likeId);
    }

    @Data
    static class CreateLikeRequest {
        private Long memberId;
        private Long postId;
    }

    @Data
    class CreateLikeResponse {
        private Long likeId;

        public CreateLikeResponse(Long id) {
            this.likeId = id;
        }
    }

    /**
     * 좋아요 갯수 조회 v1
     */
    @GetMapping("/api/v1/likes/{postId}")
    public ResultLikes likes(@PathVariable("postId") Long postId) {
        Post post = postService.findOne(postId);
        List<Like> findLikes = post.getLikes();
        return new ResultLikes(findLikes.size());
    }

    @Data
    class ResultLikes {
        private int likeCnt;

        public ResultLikes(int cnt) {
            this.likeCnt = cnt;
        }
    }



    /**
     * 좋아요 취소 v1
     */
    @DeleteMapping("/api/v1/likes/{id}")
    public void delete(@PathVariable("id") Long likeId) {
        likeService.cancel(likeId);
    }

}
