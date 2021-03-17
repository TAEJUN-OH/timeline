package com.example.timelineservice.timeline.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "팔로우 요청 객체")
public class FollowRequest {
    private Long memberId;
    private Long followMemberId;
}
