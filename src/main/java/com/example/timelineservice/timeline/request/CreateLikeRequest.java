package com.example.timelineservice.timeline.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "포스트 등록 요청 객체")
public class CreateLikeRequest {
    private Long memberId;
    private Long postId;
}
