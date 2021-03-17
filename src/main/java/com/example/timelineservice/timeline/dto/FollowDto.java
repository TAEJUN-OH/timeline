package com.example.timelineservice.timeline.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "회원 팔로우 정보 데이터 객체")
public class FollowDto {

    @ApiModelProperty(required = true, value = "아이디")
    private Long followId;

    @ApiModelProperty(required = true, value = "회원명")
    private String name;

    @ApiModelProperty(required = true, value = "회원 아이디")
    private Long memberId;

    @ApiModelProperty(required = true, value = "팔로우 회원 아이디")
    private Long followMemberId;
}
