package com.example.doctorappointmentbooking.doctor_appointment_management.internal.shell.gateway;

import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.IViewDoctorAppointment;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.output_ports.IDoctorAppointmentGateway;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.ViewAppointmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorAppointmentGateway implements IDoctorAppointmentGateway {
    private final IViewDoctorAppointment viewDoctorUpcomingAppointments;

    public DoctorAppointmentGateway(IViewDoctorAppointment viewDoctorUpcomingAppointments) {
        this.viewDoctorUpcomingAppointments = viewDoctorUpcomingAppointments;
    }

    @Override
    public CompletableFuture<List<ViewAppointmentResponse>> getUpcomingAppointments() {
        return viewDoctorUpcomingAppointments.getUpcomingAppointments()
                .thenApply(appointmentDtos -> appointmentDtos.stream().map(appointmentDto -> new ViewAppointmentResponse(appointmentDto.timeSlot(),
                        appointmentDto.patientId(),
                        appointmentDto.patientName(),
                        appointmentDto.reservedAt())).toList());
    }
}
