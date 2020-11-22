package com.kaden.iot.serviceapi.domain.service.natureremo;

import com.kanden.iot.externalapi.client.NatureRemoApiClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManipulateTService {

    private final NatureRemoApiClient natureRemoApiClient;

    public void runTv(String button){

        try {
            natureRemoApiClient.getNatureRemoInfo(button);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
