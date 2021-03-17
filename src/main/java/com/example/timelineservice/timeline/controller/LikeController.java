package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.request.CreateLikeRequest;
import com.example.timelineservice.timeline.request.DeleteLikeRequest;
import com.example.timelineservice.timeline.service.LikeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class LikeController {

    private final LikeService likeService;

    /**
     * 좋아요 등록 v1
     * @param request(memberId , postId)
     * @return likeId
     */
    @PostMapping("/likes")
    @ApiOperation(value = "좋아요 등록", notes = "좋아요 등록 API")
    public ResponseEntity<?> create(@RequestBody @Valid CreateLikeRequest request) {
        likeService.like(request.getPostId(), request.getMemberId());
        return ResponseEntity.noContent().build();
    }


    /**
     * 좋아요 취소 v1
     * @param request(likeId , postId)
     */
    @DeleteMapping("/likes")
    @ApiOperation(value = "좋아요 취소", notes = "좋아요 취소 API")
    public ResponseEntity<?> delete(@RequestBody DeleteLikeRequest request) {
        likeService.cancel(request.getLikeId() , request.getPostId());
        return ResponseEntity.noContent().build();
    }

}
