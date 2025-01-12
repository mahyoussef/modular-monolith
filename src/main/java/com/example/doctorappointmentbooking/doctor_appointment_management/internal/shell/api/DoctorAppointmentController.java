package com.example.doctorappointmentbooking.doctor_appointment_management.internal.shell.api;

import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.input_ports.DoctorAppointmentPort;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.cancel_appointment.CancelAppointment;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.complete_appointment.CompleteAppointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/v1/cancel-appointment")
    public DeferredResult<ResponseEntity<?>> cancelAppointment(@RequestBody CancelAppointment command) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        doctorAppointmentPort.cancelAppointment(command)
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.noContent().build()));
        return deferredResult;
    }

    @PostMapping("/v1/complete-appointment")
    public DeferredResult<ResponseEntity<?>> completeAppointment(@RequestBody CompleteAppointment command) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        doctorAppointmentPort.completeAppointment(command)
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.noContent().build()));
        return deferredResult;
    }
}
