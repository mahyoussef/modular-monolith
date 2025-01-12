package com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.output_ports;

import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.ViewAppointmentResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDoctorAppointmentGateway {

    CompletableFuture<List<ViewAppointmentResponse>> getUpcomingAppointments();

    CompletableFuture<Void> cancelAppointment(String appointmentId);
    CompletableFuture<Void> completeAppointment(String appointmentId);
}
