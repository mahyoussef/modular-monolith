package com.example.doctorappointmentbooking.appointment_booking.shared.events;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.Appointment;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.AppointmentEvent;

public record NewAppointmentEventDto(String appointmentId, String reservedAt, String patientName)
        implements AppointmentEvent {
    public static NewAppointmentEventDto of(Appointment appointment) {
        return new NewAppointmentEventDto(appointment.getId(),
                appointment.getReservedAt().toString(),
                appointment.getPatient().name());
    }
}
