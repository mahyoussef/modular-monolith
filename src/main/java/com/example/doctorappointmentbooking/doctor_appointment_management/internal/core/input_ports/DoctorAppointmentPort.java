package com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.input_ports;

import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.ViewAppointmentResponse;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.cancel_appointment.CancelAppointment;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.complete_appointment.CompleteAppointment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DoctorAppointmentPort {

    CompletableFuture<List<ViewAppointmentResponse>> getUpcomingAppointments();
    CompletableFuture<Void> cancelAppointment(CancelAppointment command);
    CompletableFuture<Void> completeAppointment(CompleteAppointment command);
}
