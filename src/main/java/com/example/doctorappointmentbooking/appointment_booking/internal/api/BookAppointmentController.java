package com.example.doctorappointmentbooking.appointment_booking.internal.api;

import com.example.doctorappointmentbooking.appointment_booking.internal.application.commands.BookAppointment;
import com.example.doctorappointmentbooking.appointment_booking.internal.application.commands.BookAppointmentHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class BookAppointmentController {

    private final BookAppointmentHandler bookAppointmentHandler;

    public BookAppointmentController(BookAppointmentHandler bookAppointmentHandler) {
        this.bookAppointmentHandler = bookAppointmentHandler;
    }

    @PostMapping("/v1/appointments")
    public DeferredResult<ResponseEntity<?>> bookAppointment(@RequestBody BookAppointment command) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        bookAppointmentHandler.handle(command)
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.status(HttpStatus.CREATED).body(result)));
        return deferredResult;
    }
}
