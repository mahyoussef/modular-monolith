package com.example.doctorappointmentbooking.appointment_booking.internal.api;

import com.example.doctorappointmentbooking.appointment_booking.internal.application.queries.ViewDoctorAvailableSlotsHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class ViewDoctorAvailableSlotsController {

    private final ViewDoctorAvailableSlotsHandler viewDoctorAvailableSlotsHandler;

    public ViewDoctorAvailableSlotsController(ViewDoctorAvailableSlotsHandler viewDoctorAvailableSlotsHandler) {
        this.viewDoctorAvailableSlotsHandler = viewDoctorAvailableSlotsHandler;
    }

    @GetMapping("/v1/doctor-available-slots")
    public DeferredResult<ResponseEntity<?>> viewDoctorAvailableSlots() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        viewDoctorAvailableSlotsHandler.handle()
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.ok(result)));
        return deferredResult;
    }
}
