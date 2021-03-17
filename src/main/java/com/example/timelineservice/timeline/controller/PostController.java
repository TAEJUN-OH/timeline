package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Post;
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

    private final PostService postService;

    /**
     * 포스트 등록 v1
     * @param memberId
     * @param request(content)
     * @return postId
     */
    @PostMapping("/api/v1/posts/{memberId}")
    public CreatePostResponse createPost(@PathVariable("memberId") Long memberId, @RequestBody @Valid CreatePostRequest request) {
        Long postedId = postService.post(memberId, request.getContent());
        return new CreatePostResponse(postedId);
    }

    @Data
    static class CreatePostRequest {
        private String content;
    }


    @Data
    static class CreatePostResponse {
        private Long postId;

        public CreatePostResponse(Long id) {
            this.postId = id;
        }
    }

    /**
     * 포스트 조회 v1
     * @param memberId
     * @return PostDto
     */
    @GetMapping("/api/v1/posts/{memberId}")
    public Result findByPost(@PathVariable Long memberId) {
        List<Post> findPosts = postService.findByPosts(memberId);
        List<PostDto> collect = findPosts.stream()
                .map(m -> new PostDto(m.getId() , m.getMember().getName() , m.getMember().getEmail() , m.getContent() , m.getLikeCnt()))
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
        private Long id;
        private String name;
        private String email;
        private String content;
        private Integer likeCnt;
    }


    /**
     * 포스트 수정 v1
     * @param postId
     * @param request(content)
     */
    @PostMapping("/api/v1/{postId}/posts")
    public void updatePost(@PathVariable("postId") Long postId, @RequestBody @Valid UpdatePostRequest request) {
        postService.update(postId, request.getContent());
    }

    @Data
    static class UpdatePostRequest {
        private String content;
    }


    /**
     * 포스트 삭제 v1
     * @param postId
     */
    @DeleteMapping("/api/v1/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
