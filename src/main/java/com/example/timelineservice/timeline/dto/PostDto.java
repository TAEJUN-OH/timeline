package com.example.timelineservice.timeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "포스트 정보 데이터 객체")
public class PostDto {

    @ApiModelProperty(required = true, value = "아이디")
    private Long id;

    @ApiModelProperty(required = true, value = "회원명")
    private String name;

    @ApiModelProperty(required = true, value = "이메일")
    private String email;

    @ApiModelProperty(required = true, value = "컨텐츠")
    private String content;

    @ApiModelProperty(required = true, value = "좋아요 수")
    private Integer likeCnt;
}
