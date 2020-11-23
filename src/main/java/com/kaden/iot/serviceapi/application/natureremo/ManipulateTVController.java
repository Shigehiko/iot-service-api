package com.kaden.iot.serviceapi.application.natureremo;

import com.kaden.iot.serviceapi.application.resource.natureremo.ManipulateTVResponse;
import com.kaden.iot.serviceapi.application.resource.natureremo.ManipulateTVResponseFactory;
import com.kaden.iot.serviceapi.domain.service.natureremo.ManipulateTService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "TV操作用", description = " ")
public class ManipulateTVController {

    private final ManipulateTService manipulateTService;
    private final ManipulateTVResponseFactory manipulateTVResponseFactory;

    /**
     * TV操作
     *
     * @param button　ボタン
     * @return ManipulateTVResponse
     */
    @GetMapping(value = "/v1/appliances/tv")
    public ManipulateTVResponse manipulateTv(
            @ApiParam(value = "ボタン", required = true, example = "power")
            @Valid @Pattern(regexp = "[power]", message = "invalid format button")
            @RequestParam(name = "button") String button
    ) {

        //TV操作APIをコール
        manipulateTService.runTv(button);

        return manipulateTVResponseFactory.create();
    }
}
