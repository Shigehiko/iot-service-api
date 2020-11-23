package com.kaden.iot.serviceapi.application.resource.natureremo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ManipulateTVResponse {

    @ApiModelProperty(value = "結果", example = "success", position = 1)
    private String result;
}
