package com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment;

import java.util.concurrent.CompletableFuture;

public interface ICompleteAppointment {
    CompletableFuture<Void> complete(String appointmentId);
}
