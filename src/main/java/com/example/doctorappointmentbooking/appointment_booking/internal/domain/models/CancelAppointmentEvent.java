package com.example.doctorappointmentbooking.appointment_booking.internal.domain.models;

public record CancelAppointmentEvent(String appointmentId, String reservedAt, String patientName)
        implements AppointmentEvent {
    public static CancelAppointmentEvent of(Appointment appointment) {
        return new CancelAppointmentEvent(appointment.getId(),
                appointment.getReservedAt().toString(),
                appointment.getPatient().name());
    }
}
