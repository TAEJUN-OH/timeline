package com.example.timelineservice.timeline.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "포스트 요청 객체")
public class PostRequest {
    private String content;
}
