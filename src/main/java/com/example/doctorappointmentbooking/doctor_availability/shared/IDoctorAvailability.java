package com.example.doctorappointmentbooking.doctor_availability.shared;

import com.example.doctorappointmentbooking.doctor_availability.internal.data.TimeSlot;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDoctorAvailability {

    CompletableFuture<List<TimeSlotDto>> getAvailableTimeSlots();
}
