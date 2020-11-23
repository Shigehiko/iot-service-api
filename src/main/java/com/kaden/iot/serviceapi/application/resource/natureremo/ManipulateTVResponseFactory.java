package com.kaden.iot.serviceapi.application.resource.natureremo;

import org.springframework.stereotype.Component;

@Component
public class ManipulateTVResponseFactory {

    /**
     * ManipulateTVResponseを生成
     *
     * @return ManipulateTVResponse
     */
    public ManipulateTVResponse create() {
        return ManipulateTVResponse.builder()
                .result("success")
                .build();
    }

}
