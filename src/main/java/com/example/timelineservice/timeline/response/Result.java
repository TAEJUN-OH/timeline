package com.example.timelineservice.timeline.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "처리결과 전달을 위한 객체")
public class Result<T> {

    @ApiModelProperty(notes = "배열을 json 으로 감싸기 위한 변수")
    private T data;
}
