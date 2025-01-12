package com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IAppointmentRepository {
    CompletableFuture<Void> save(Appointment appointment);
    CompletableFuture<List<Appointment>> getNewAppointments();
    CompletableFuture<Optional<Appointment>> getAppointmentById(String appointmentId);
}
