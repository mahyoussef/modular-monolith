package com.example.doctorappointmentbooking.appointment_booking.internal.application.commands;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts.IAppointmentRepository;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Patient;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BookAppointmentHandler {

    private final IAppointmentRepository appointmentRepository;

    public BookAppointmentHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public CompletableFuture<BookAppointmentResponse> handle(BookAppointment command) {
        // TODO: Check validation of free slot first (gateway to fetch doctor available slots)
        Appointment appointment = Appointment.newAppointment(command.slotId(),
                new Patient(command.patientId(), command.patientName()),
                command.reservedAt());
        return appointmentRepository.save(appointment).thenApply(v -> toBookAppointment(appointment));
    }

    private BookAppointmentResponse toBookAppointment(Appointment appointment) {
        return new BookAppointmentResponse(appointment.getId(), appointment.getSlotId(), appointment.getReservedAt());
    }
}
