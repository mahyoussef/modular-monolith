package com.example.doctorappointmentbooking.appointment_booking.internal.application.commands.book_appointment;

import java.time.LocalDateTime;

public record BookAppointmentResponse(String id, String slotId, LocalDateTime reservedAt) {
}
