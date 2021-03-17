package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.service.LikeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    /**
     * 좋아요 등록 v1
     * @param request(memberId , postId)
     * @return likeId
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
     * 좋아요 취소 v1
     * @param request(likeId , postId)
     */
    @DeleteMapping("/api/v1/likes")
    public void delete(@RequestBody DeleteLikeRequest request) {
        likeService.cancel(request.getLikeId() , request.getPostId());
    }

    @Data
    static class DeleteLikeRequest {
        private Long likeId;
        private Long postId;
    }

}
