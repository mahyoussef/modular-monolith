package com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment;

import java.util.concurrent.CompletableFuture;

public interface ICancelAppointment {
    CompletableFuture<Void> cancel(String appointmentId);
}
