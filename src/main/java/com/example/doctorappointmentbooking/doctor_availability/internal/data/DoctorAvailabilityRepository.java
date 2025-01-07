package com.example.doctorappointmentbooking.doctor_availability.internal.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface DoctorAvailabilityRepository {
    CompletableFuture<List<TimeSlot>> getTimeSlots();
    CompletableFuture<Void> save(TimeSlot timeSlotToAdd);
    CompletableFuture<Optional<TimeSlot>> getTimeSlotByDoctorAndDateTime(String doctorId, LocalDateTime date);
}
