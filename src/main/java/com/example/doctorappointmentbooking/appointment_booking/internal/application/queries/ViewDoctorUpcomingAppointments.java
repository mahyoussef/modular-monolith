package com.example.doctorappointmentbooking.appointment_booking.internal.application.queries;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts.IAppointmentRepository;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.AppointmentDto;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.IViewDoctorAppointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ViewDoctorUpcomingAppointments implements IViewDoctorAppointment {
    private final IAppointmentRepository appointmentRepository;

    public ViewDoctorUpcomingAppointments(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public CompletableFuture<List<AppointmentDto>> getUpcomingAppointments() {
        return appointmentRepository.getNewAppointments()
                .thenApply(appointments -> appointments.stream()
                        .map(appointment -> new AppointmentDto(appointment.getSlotId(),
                                appointment.getPatient().id(),
                                appointment.getPatient().name(),
                                appointment.getReservedAt().toString())).toList());
    }
}
