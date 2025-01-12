package com.example.doctorappointmentbooking.appointment_booking.internal.application.commands.book_appointment;

public record BookAppointment(String slotId, String patientId, String patientName, String reservedAt) {
}
