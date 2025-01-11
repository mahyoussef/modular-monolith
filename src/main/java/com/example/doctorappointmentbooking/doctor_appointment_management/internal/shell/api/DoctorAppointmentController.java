package com.example.doctorappointmentbooking.doctor_appointment_management.internal.shell.api;

import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.input_ports.DoctorAppointmentPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class DoctorAppointmentController {

    private final DoctorAppointmentPort doctorAppointmentPort;

    public DoctorAppointmentController(DoctorAppointmentPort doctorAppointmentPort) {
        this.doctorAppointmentPort = doctorAppointmentPort;
    }

    @GetMapping("/v1/upcoming-appointments")
    public DeferredResult<ResponseEntity<?>> getAllUpComingAppointments() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        doctorAppointmentPort.getUpcomingAppointments()
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.ok(result)));
        return deferredResult;
    }
}
