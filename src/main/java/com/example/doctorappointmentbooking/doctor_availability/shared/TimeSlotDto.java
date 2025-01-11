package com.example.doctorappointmentbooking.doctor_availability.shared;

import java.time.LocalDateTime;

public record TimeSlotDto(
        String id,
        LocalDateTime date,
        String doctorId,
        String doctorName,
        double cost
) {
}
