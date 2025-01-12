package com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services;

import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.input_ports.DoctorAppointmentPort;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.output_ports.IDoctorAppointmentGateway;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.cancel_appointment.CancelAppointment;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.complete_appointment.CompleteAppointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorAppointmentService implements DoctorAppointmentPort {

    private final IDoctorAppointmentGateway doctorAppointmentGateway;

    public DoctorAppointmentService(IDoctorAppointmentGateway doctorAppointmentGateway) {
        this.doctorAppointmentGateway = doctorAppointmentGateway;
    }

    public CompletableFuture<List<ViewAppointmentResponse>> getUpcomingAppointments() {
        return doctorAppointmentGateway.getUpcomingAppointments();
    }

    public CompletableFuture<Void> cancelAppointment(CancelAppointment command) {
        return doctorAppointmentGateway.cancelAppointment(command.appointmentId());
    }

    public CompletableFuture<Void> completeAppointment(CompleteAppointment command) {
        return doctorAppointmentGateway.completeAppointment(command.appointmentId());
    }
}
