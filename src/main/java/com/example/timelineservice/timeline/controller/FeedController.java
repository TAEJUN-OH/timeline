package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Post;
import com.example.timelineservice.timeline.response.NewsFeedResponse;
import com.example.timelineservice.timeline.response.Result;
import com.example.timelineservice.timeline.service.PostService;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class FeedController {

    private final PostService postService;

    /**
     * 뉴스 피드 조회 v1
     * @param memberId
     * @return NewsFeedResponse
     */
    @GetMapping("/feeds/{memberId}")
    @ApiOperation(value = "뉴스 피드 조회", notes = "뉴스 피드(my Post or Follow Post) 를 조회하는 API ")
    public Result feeds(@PathVariable("memberId") Long memberId) {
        List<Post> newsFeed = postService.findByNewsFeed(memberId);
        List<NewsFeedResponse> collect = newsFeed.stream()
                .map(m -> new NewsFeedResponse(m.getMember().getId(), m.getId() ,m.getContent(), m.getMember().getName(), m.getLikeCnt()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

}

