package com.marianbastiurea.service;

import com.marianbastiurea.client.FlightReservationServiceClient;
import com.marianbastiurea.client.FlightSearchServiceClient;
import com.marianbastiurea.dto.FlightReservationRequest;
import com.marianbastiurea.dto.FlightReservationResponse;
import com.marianbastiurea.dto.TripReservationRequest;
import com.marianbastiurea.dto.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final FlightSearchServiceClient searchServiceClient;
    private final FlightReservationServiceClient reservationServiceClient;

    public TripReservationService(FlightSearchServiceClient searchServiceClient, FlightReservationServiceClient reservationServiceClient) {
        this.searchServiceClient = searchServiceClient;
        this.reservationServiceClient = reservationServiceClient;
    }

    public FlightReservationResponse reserve(TripReservationRequest request){
        var flights = this.searchServiceClient.getFlights(request.departure(), request.arrival());
        var bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
        var flight = bestDeal.orElseThrow(() -> new IllegalStateException("no flights found"));
        var reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(), request.date());
        return this.reservationServiceClient.reserve(reservationRequest);
    }

}