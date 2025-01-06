package com.example.doctorappointmentbooking.doctor_availability.internal.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DoctorAvailabilityRepository {
    CompletableFuture<List<TimeSlot>> getTimeSlots();
    CompletableFuture<Void> save(TimeSlot timeSlotToAdd);
}
