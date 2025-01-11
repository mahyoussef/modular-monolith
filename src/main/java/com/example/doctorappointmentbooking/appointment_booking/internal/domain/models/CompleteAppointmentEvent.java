package com.example.doctorappointmentbooking.appointment_booking.internal.domain.models;

public record CompleteAppointmentEvent(String appointmentId, String reservedAt, String patientName)
        implements AppointmentEvent {
    public static CompleteAppointmentEvent of(Appointment appointment) {
        return new CompleteAppointmentEvent(appointment.getId(),
                appointment.getReservedAt().toString(),
                appointment.getPatient().name());
    }
}
