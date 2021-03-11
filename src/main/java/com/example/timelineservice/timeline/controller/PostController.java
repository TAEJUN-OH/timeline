package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.service.MemberService;
import com.example.timelineservice.timeline.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final MemberService memberService;
    private final PostService postService;

//    @PostMapping("/api/v1/post/{memberId}")
//    public CreatePostResponse createPost(@PathVariable("memberId") Long memberId, String contents) {
//
//    }
//
//    @Data
//    static class CreatePostRequest {
//    }
//
//
//    @Data
//    static class CreatePostResponse {
//
//    }

}
