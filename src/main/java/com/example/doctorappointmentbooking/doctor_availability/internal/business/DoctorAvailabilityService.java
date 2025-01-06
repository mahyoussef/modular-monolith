package com.example.doctorappointmentbooking.doctor_availability.internal.business;

import com.example.doctorappointmentbooking.doctor_availability.internal.business.models.AddTimeSlotRequest;
import com.example.doctorappointmentbooking.doctor_availability.internal.data.DoctorAvailabilityRepository;
import com.example.doctorappointmentbooking.doctor_availability.internal.data.TimeSlot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorAvailabilityService {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    public DoctorAvailabilityService(DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public CompletableFuture<List<TimeSlot>> getAllSlots() {
        return doctorAvailabilityRepository.getTimeSlots();
    }

    public CompletableFuture<TimeSlot> addTimeSlot(AddTimeSlotRequest timeSlotToAdd) {
        TimeSlot createdTimeSlot = createTimeSlot(timeSlotToAdd);
        return doctorAvailabilityRepository.save(createdTimeSlot).thenApply(v -> createdTimeSlot);
    }

    private TimeSlot createTimeSlot(AddTimeSlotRequest timeSlotToAdd) {
        String timeSlotId = UUID.randomUUID().toString();
        return new TimeSlot(timeSlotId,
                timeSlotToAdd.date(),
                timeSlotToAdd.doctorId(),
                timeSlotToAdd.doctorName(),
                timeSlotToAdd.isReserved(),
                timeSlotToAdd.cost());
    }
}
