package com.example.doctorappointmentbooking.doctor_availability.internal.business;

import com.example.doctorappointmentbooking.doctor_availability.internal.business.models.AddTimeSlotRequest;
import com.example.doctorappointmentbooking.doctor_availability.internal.business.models.TimeSlotAlreadyExistsException;
import com.example.doctorappointmentbooking.doctor_availability.internal.data.DoctorAvailabilityRepository;
import com.example.doctorappointmentbooking.doctor_availability.internal.data.TimeSlot;
import com.example.doctorappointmentbooking.doctor_availability.shared.IDoctorAvailability;
import com.example.doctorappointmentbooking.doctor_availability.shared.TimeSlotDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorAvailabilityService implements IDoctorAvailability {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    public DoctorAvailabilityService(DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    public CompletableFuture<List<TimeSlot>> getAllTimeSlots() {
        return doctorAvailabilityRepository.getTimeSlots();
    }

    public CompletableFuture<TimeSlot> addTimeSlot(AddTimeSlotRequest timeSlotToAdd) {
        return getTimeSlotBy(timeSlotToAdd.doctorId(), timeSlotToAdd.date()).thenCompose(timeSlot -> {
            timeSlot.ifPresent(ts -> {
                String errorMessage = "Time slot already exists for: %s on %s".formatted(ts.doctorName(), ts.date());
                throw new TimeSlotAlreadyExistsException(errorMessage);
            });
            TimeSlot createdTimeSlot = createTimeSlot(timeSlotToAdd);
            return doctorAvailabilityRepository.save(createdTimeSlot).thenApply(v -> createdTimeSlot);
        });
    }

    private TimeSlot createTimeSlot(AddTimeSlotRequest timeSlotToAdd) {
        String timeSlotId = UUID.randomUUID().toString();
        LocalDateTime dateTime = LocalDateTime.parse(timeSlotToAdd.date());
        return new TimeSlot(timeSlotId,
                dateTime,
                timeSlotToAdd.doctorId(),
                timeSlotToAdd.doctorName(),
                timeSlotToAdd.isReserved(),
                timeSlotToAdd.cost());
    }

    private CompletableFuture<Optional<TimeSlot>> getTimeSlotBy(String doctorId, String timeSlotDate) {
        LocalDateTime dateTime = LocalDateTime.parse(timeSlotDate);
        return doctorAvailabilityRepository.getTimeSlotByDoctorAndDateTime(doctorId, dateTime);
    }

    public CompletableFuture<List<TimeSlotDto>> getAvailableTimeSlots() {
        return getAllTimeSlots().thenApply(timeSlots -> timeSlots.stream()
                .filter(timeSlot -> !timeSlot.isReserved())
                .map(timeSlot -> new TimeSlotDto(timeSlot.id(), timeSlot.date(),
                        timeSlot.doctorId(), timeSlot.doctorName(), timeSlot.cost())).toList());
    }
}
