package com.example.timelineservice.timeline.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "뉴스피드 조회 응답 객체")
public class NewsFeedResponse {

    private Long memberId;
    private Long postId;
    private String content;
    private String name;
    private Integer likeCnt;

}
