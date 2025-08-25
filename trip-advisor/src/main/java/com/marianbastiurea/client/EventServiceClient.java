package com.marianbastiurea.client;

import com.marianbastiurea.dto.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class EventServiceClient {

    private final RestClient client;

    public EventServiceClient(RestClient client) {
        this.client = client;
    }

    public List<Event> getEvents(String airportCode){
        return  this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Event>>() {
                });
    }
}