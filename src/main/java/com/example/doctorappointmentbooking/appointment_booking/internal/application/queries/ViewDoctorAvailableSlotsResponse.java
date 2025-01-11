package com.example.doctorappointmentbooking.appointment_booking.internal.application.queries;

import java.time.LocalDateTime;

public record ViewDoctorAvailableSlotsResponse(
        String id,
        LocalDateTime date,
        String doctorId,
        String doctorName,
        double cost
) {
}
