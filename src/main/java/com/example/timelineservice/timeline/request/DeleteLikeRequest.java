package com.example.timelineservice.timeline.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "좋아요 삭제 요청 객체")
public class DeleteLikeRequest {
    private Long likeId;
    private Long postId;
}
