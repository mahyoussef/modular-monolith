package com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services;

public record ViewAppointmentResponse(String timeSlot, String patientId, String patientName, String reservedAt) {
}
