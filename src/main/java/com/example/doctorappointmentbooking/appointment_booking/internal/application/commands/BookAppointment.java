package com.example.doctorappointmentbooking.appointment_booking.internal.application.commands;

public record BookAppointment(String slotId, String patientId, String patientName, String reservedAt) {
}
