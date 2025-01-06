package com.example.doctorappointmentbooking.doctor_availability.internal.data;

import java.time.LocalDateTime;
import java.util.UUID;

public record TimeSlot(
        String id,
        LocalDateTime date,
        String doctorId,
        String doctorName,
        boolean isReserved,
        double cost
){}