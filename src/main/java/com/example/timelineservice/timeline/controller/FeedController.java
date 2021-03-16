package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final PostService postService;

    /**
     * 뉴스 피드 v1
     */

    @GetMapping("/api/v1/feeds/{memberId}")
    public Result feeds(@PathVariable("memberId") Long memberId) {
        List<Post> newsFeed = postService.findByNewsFeed(memberId);
        List<FeedDto> collect = newsFeed.stream()
                .map(m -> new FeedDto(m.getMember().getId(), m.getId() ,m.getContent(), m.getMember().getName(), m.getLikeCnt()))
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
    class FeedDto {
        private Long memberId;
        private Long postId;
        private String content;
        private String name;
        private Integer likeCnt;
    }
}

