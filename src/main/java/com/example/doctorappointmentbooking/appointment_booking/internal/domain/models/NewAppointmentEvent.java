package com.example.doctorappointmentbooking.appointment_booking.internal.domain.models;

public record NewAppointmentEvent(String appointmentId, String reservedAt, String patientName)
        implements AppointmentEvent {
    public static NewAppointmentEvent of(Appointment appointment) {
        return new NewAppointmentEvent(appointment.getId(),
                appointment.getReservedAt().toString(),
                appointment.getPatient().name());
    }
}
