package com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;

import java.util.concurrent.CompletableFuture;

public interface IAppointmentRepository {
    CompletableFuture<Void> save(Appointment appointment);
}
