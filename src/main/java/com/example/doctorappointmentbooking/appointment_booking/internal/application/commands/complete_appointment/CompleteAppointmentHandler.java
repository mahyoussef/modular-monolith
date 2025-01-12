package com.example.doctorappointmentbooking.appointment_booking.internal.application.commands.complete_appointment;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts.IAppointmentRepository;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.ICancelAppointment;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.ICompleteAppointment;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CompleteAppointmentHandler implements ICompleteAppointment {

    private final IAppointmentRepository appointmentRepository;

    public CompleteAppointmentHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public CompletableFuture<Void> complete(String appointmentId) {
        return appointmentRepository.getAppointmentById(appointmentId)
                .thenApply(appointment -> {
                    if (appointment.isEmpty()) {
                        // TODO: throw not found exception and handle it
                        throw new RuntimeException("Application not found!");
                    }
                    Appointment toCompleteAppointment = appointment.get();
                    Appointment completedAppointment =
                            Appointment.completeAppointment(appointmentId,
                                    toCompleteAppointment.getSlotId(),
                                    toCompleteAppointment.getPatient(),
                                    toCompleteAppointment.getReservedAt().toString());
                    appointmentRepository.save(completedAppointment);
                    return null;
                });
    }
}
