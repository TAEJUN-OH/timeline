package com.example.timelineservice.timeline.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "회원 요청 객체")
public class MemberRequest {
    private String name;
    private String email;
}
