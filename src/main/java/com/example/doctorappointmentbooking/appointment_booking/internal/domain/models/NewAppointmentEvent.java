package com.example.doctorappointmentbooking.appointment_booking.internal.domain.models;

public record NewAppointmentEvent(String appointmentId, String slotId, String patientId, String reservedAt)
        implements AppointmentEvent {
    public static NewAppointmentEvent of(Appointment appointment) {
        return new NewAppointmentEvent(appointment.getId(),
                appointment.getSlotId(),
                appointment.getPatient().id(),
                appointment.getReservedAt().toString());
    }
}
