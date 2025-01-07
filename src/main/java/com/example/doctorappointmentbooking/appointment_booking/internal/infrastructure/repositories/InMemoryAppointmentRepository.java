package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.repositories;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.contracts.IAppointmentRepository;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class InMemoryAppointmentRepository implements IAppointmentRepository {

    public CompletableFuture<Void> save(Appointment appointment) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Saved appointment");
            return null;
        });
    }
}
