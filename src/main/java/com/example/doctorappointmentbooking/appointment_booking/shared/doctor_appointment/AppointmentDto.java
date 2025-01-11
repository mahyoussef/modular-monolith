package com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment;

public record AppointmentDto(String timeSlot, String patientId, String patientName, String reservedAt) {
}
