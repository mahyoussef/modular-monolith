package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.repositories;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts.IAppointmentRepository;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.AppointmentStatus;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Patient;
import com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.events.AppointmentEventPublisher;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class InMemoryAppointmentRepository implements IAppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<>(
            List.of(
                    Appointment.of(UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Patient(UUID.randomUUID().toString(), "Test Patient"),
                            LocalDateTime.now().plusDays(1),
                            AppointmentStatus.NEW),
                    Appointment.of(UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Patient(UUID.randomUUID().toString(), "Test Patient"),
                            LocalDateTime.now().plusDays(1),
                            AppointmentStatus.NEW),
                    Appointment.of(UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Patient(UUID.randomUUID().toString(), "Test Patient"),
                            LocalDateTime.now().plusDays(1),
                            AppointmentStatus.NEW),
                    Appointment.of(UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Patient(UUID.randomUUID().toString(), "Test Patient"),
                            LocalDateTime.now().minusDays(1),
                            AppointmentStatus.COMPLETED),
                    Appointment.of(UUID.randomUUID().toString(),
                            UUID.randomUUID().toString(),
                            new Patient(UUID.randomUUID().toString(), "Test Patient"),
                            LocalDateTime.now().minusDays(2),
                            AppointmentStatus.CANCELLED)
            )
    );
    private final AppointmentEventPublisher appointmentEventPublisher;

    public InMemoryAppointmentRepository(AppointmentEventPublisher appointmentEventPublisher) {
        this.appointmentEventPublisher = appointmentEventPublisher;
    }

    public CompletableFuture<Void> save(Appointment appointment) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Saved appointment");
            appointment.getOccurredEvents().forEach(appointmentEventPublisher::publish);
            return null;
        });
    }

    public CompletableFuture<List<Appointment>> getNewAppointments() {
        return CompletableFuture.supplyAsync(() ->
                appointments.stream().filter(Appointment::isUpcomingAppointment).toList());
    }

    public CompletableFuture<Optional<Appointment>> getAppointmentById(String appointmentId) {
        return CompletableFuture.supplyAsync(() ->
                appointments.stream()
                        .filter(appointment -> appointment.getId().equalsIgnoreCase(appointmentId)).findFirst());
    }
}
