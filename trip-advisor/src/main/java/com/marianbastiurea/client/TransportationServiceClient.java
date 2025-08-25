package com.marianbastiurea.client;

import com.marianbastiurea.dto.Transportation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class TransportationServiceClient {

    private final RestClient restClient;

    public TransportationServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Transportation getTransportation(String airportCode) {
        return this.restClient.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Transportation.class);
    }

}