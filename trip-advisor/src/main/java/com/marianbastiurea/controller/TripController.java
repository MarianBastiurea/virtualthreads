package com.marianbastiurea.controller;

import com.marianbastiurea.dto.FlightReservationResponse;
import com.marianbastiurea.dto.TripPlan;
import com.marianbastiurea.dto.TripReservationRequest;
import com.marianbastiurea.service.TripPlanService;
import com.marianbastiurea.service.TripReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")

public class TripController {

    private final TripPlanService planService;
    private final TripReservationService reservationService;


    public TripController(TripPlanService planService, TripReservationService reservationService) {
        this.planService = planService;
        this.reservationService = reservationService;
    }

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable("airportCode") String airportCode) {
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request) {
        return this.reservationService.reserve(request);
    }

}