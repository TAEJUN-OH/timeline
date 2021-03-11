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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final MemberService memberService;
    private final PostService postService;


    /**
     * 포스트 등록 v1
     */
    @PostMapping("/api/v1/posts/{memberId}")
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

    /**
     * 포스트 조회 v1
     */
    @GetMapping("/api/v1/posts/{memberId}")
    public Result posts(@PathVariable Long memberId) {
        Member member = memberService.findOne(memberId);
        List<Post> findPosts = member.getPosts();
        List<PostDto> collect = findPosts.stream()
                .map(m -> new PostDto(m.getContent()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class PostDto {
        private String content;
    }

    /**
     * 포스트 삭제 v1
     */
    @DeleteMapping("/api/v1/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
