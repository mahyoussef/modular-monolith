package com.example.doctorappointmentbooking.doctor_availability.internal.data;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class DoctorAvailabilityInMemoryRepository implements DoctorAvailabilityRepository {
    private final List<TimeSlot> timeSlots = new ArrayList<>(
            List.of(
                    new TimeSlot(UUID.randomUUID().toString(),
                            LocalDateTime.of(2025, 2, 1, 12, 0),
                            UUID.randomUUID().toString(),
                            "Mahmoud Youssef",
                            false,
                            300),
                    new TimeSlot(UUID.randomUUID().toString(),
                            LocalDateTime.of(2025, 2, 1, 14, 0),
                            UUID.randomUUID().toString(),
                            "Mahmoud Youssef",
                            false,
                            300),
                    new TimeSlot(UUID.randomUUID().toString(),
                            LocalDateTime.of(2025, 2, 1, 16, 0),
                            UUID.randomUUID().toString(),
                            "Mahmoud Youssef",
                            false,
                            300)
            )
    );

    public CompletableFuture<List<TimeSlot>> getTimeSlots() {
        return CompletableFuture.supplyAsync(() -> timeSlots);
    }

    public CompletableFuture<Void> save(TimeSlot timeSlotToAdd) {
        return CompletableFuture.supplyAsync(() -> {
            timeSlots.add(timeSlotToAdd);
            return null;
        });
    }

    public CompletableFuture<Optional<TimeSlot>> getTimeSlotByDoctorAndDateTime(String doctorId, LocalDateTime date) {
        return CompletableFuture.supplyAsync(() -> timeSlots.stream()
                .filter(ts -> ts.doctorId().equalsIgnoreCase(doctorId) && ts.date().isEqual(date))
                .findFirst());
    }
}
