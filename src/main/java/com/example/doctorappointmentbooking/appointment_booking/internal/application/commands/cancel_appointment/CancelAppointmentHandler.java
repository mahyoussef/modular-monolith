package com.example.doctorappointmentbooking.appointment_booking.internal.application.commands.cancel_appointment;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts.IAppointmentRepository;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.ICancelAppointment;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CancelAppointmentHandler implements ICancelAppointment {

    private final IAppointmentRepository appointmentRepository;

    public CancelAppointmentHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public CompletableFuture<Void> cancel(String appointmentId) {
        return appointmentRepository.getAppointmentById(appointmentId)
                .thenApply(appointment -> {
                    if (appointment.isEmpty()) {
                        // TODO: throw not found exception and handle it
                        throw new RuntimeException("Application not found!");
                    }
                    Appointment toCancelAppointment = appointment.get();
                    Appointment cancelledAppointment =
                            Appointment.cancelAppointment(appointmentId,
                                    toCancelAppointment.getSlotId(),
                                    toCancelAppointment.getPatient(),
                                    toCancelAppointment.getReservedAt().toString());
                    appointmentRepository.save(cancelledAppointment);
                    return null;
                });
    }
}
