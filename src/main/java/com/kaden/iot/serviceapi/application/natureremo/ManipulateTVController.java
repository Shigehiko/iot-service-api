package com.kaden.iot.serviceapi.application.natureremo;

import com.kaden.iot.serviceapi.application.resource.natureremo.ManipulateTVResponse;
import com.kaden.iot.serviceapi.application.resource.natureremo.ManipulateTVResponseFactory;
import com.kaden.iot.serviceapi.domain.service.natureremo.ManipulateTService;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class ManipulateTVController {

    private final ManipulateTService manipulateTService;
    private final ManipulateTVResponseFactory manipulateTVResponseFactory;

    @GetMapping(value = "/1/appliances/tv")
    public ManipulateTVResponse manipulateTv(
            @Valid @Pattern(regexp = "[power]", message = "invalid format button")
            @RequestParam(name = "button") String button
    ) {

        manipulateTService.runTv(button);

        return manipulateTVResponseFactory.create();
    }
}
