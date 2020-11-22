package com.kaden.iot.serviceapi.application.resource.natureremo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class ManipulateTVResponseFactory {

    public ManipulateTVResponse create() {
        return ManipulateTVResponse.builder()
                .result("success")
                .build();
    }

}
