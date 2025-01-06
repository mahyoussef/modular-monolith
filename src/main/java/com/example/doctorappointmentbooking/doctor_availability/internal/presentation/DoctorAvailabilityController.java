package com.example.doctorappointmentbooking.doctor_availability.internal.presentation;

import com.example.doctorappointmentbooking.doctor_availability.internal.business.DoctorAvailabilityService;
import com.example.doctorappointmentbooking.doctor_availability.internal.business.models.AddTimeSlotRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService doctorAvailabilityService;

    public DoctorAvailabilityController(DoctorAvailabilityService doctorAvailabilityService) {
        this.doctorAvailabilityService = doctorAvailabilityService;
    }

    @GetMapping("/v1/time-slots")
    public DeferredResult<ResponseEntity<?>> getAllTimeSlots() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        doctorAvailabilityService.getAllSlots()
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.ok().body(result)));
        return deferredResult;
    }

    @PostMapping("/v1/time-slots")
    public DeferredResult<ResponseEntity<?>> addTimeSlot(@RequestBody AddTimeSlotRequest timeSlotRequest) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        doctorAvailabilityService.addTimeSlot(timeSlotRequest)
                        .thenAccept(result -> deferredResult.setResult(ResponseEntity.status(HttpStatus.CREATED)
                                .body(result)));
        return deferredResult;
    }
}
