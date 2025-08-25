package com.marianbastiurea.client;

import com.marianbastiurea.dto.FlightReservationRequest;
import com.marianbastiurea.dto.FlightReservationResponse;
import org.springframework.web.client.RestClient;


public class FlightReservationServiceClient {

    private final RestClient client;

    public FlightReservationServiceClient(RestClient client) {
        this.client = client;
    }

    public FlightReservationResponse reserve(FlightReservationRequest request) {
        return this.client.post()
                .body(request)
                .retrieve()
                .body(FlightReservationResponse.class);
    }

}