package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.dto.PostDto;
import com.example.timelineservice.timeline.request.PostRequest;
import com.example.timelineservice.timeline.response.Result;
import com.example.timelineservice.timeline.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class PostController {

    private final PostService postService;

    /**
     * 포스트 등록 v1
     * @param memberId
     * @param request(content)
     * @return postId
     */
    @PostMapping("/posts/{memberId}")
    @ApiOperation(value = "포스트 등록", notes = "포스트를 등록하는 API")
    public ResponseEntity<?> createPost(@PathVariable("memberId") Long memberId, @RequestBody @Valid PostRequest request) {
        postService.post(memberId, request.getContent());
        return ResponseEntity.noContent().build();
    }


    /**
     * 포스트 조회 v1
     * @param memberId
     * @return PostDto
     */
    @GetMapping("/posts/{memberId}")
    @ApiOperation(value = "포스트 조회", notes = "포스트를 조회하는 API")
    public Result findByPost(@PathVariable Long memberId) {
        List<Post> findPosts = postService.findByPosts(memberId);
        List<PostDto> collect = findPosts.stream()
                .map(m -> new PostDto(m.getId() , m.getMember().getName() , m.getMember().getEmail() , m.getContent() , m.getLikeCnt()))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    /**
     * 포스트 수정 v1
     * @param postId
     * @param request(content)
     */
    @PostMapping("/{postId}/posts")
    @ApiOperation(value = "포스트 내용 수정", notes = "포스트 내용을 수정하는 API")
    public ResponseEntity<?> updatePost(@PathVariable("postId") Long postId, @RequestBody @Valid PostRequest request) {
        postService.update(postId, request.getContent());
        return ResponseEntity.noContent().build();
    }


    /**
     * 포스트 삭제 v1
     * @param postId
     */
    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "포스트 삭제", notes = "포스트 삭제 API")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }
}
