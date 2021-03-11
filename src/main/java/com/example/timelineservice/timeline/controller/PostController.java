package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.service.MemberService;
import com.example.timelineservice.timeline.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final MemberService memberService;
    private final PostService postService;

    @PostMapping("/api/v1/post/{memberId}")
    public void createPost(@PathVariable("memberId") Long memberId, @RequestBody @Valid CreatePostRequest request) {
        postService.post(memberId, request.getContent());
    }

    @Data
    static class CreatePostRequest { //클라이언트에서 넘어온 요청 파라미터
        private String content;
    }


    @Data
    static class CreatePostResponse { //클라이언트로 보내줄 데이터

    }

//    @GetMapping("/api/v1/post")
//    public Result posts() {
////        List<Post> posts =
//    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class PostDto {
        private String name;
        private String content;
    }
}
