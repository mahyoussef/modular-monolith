package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.events;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.AppointmentEvent;

public interface IAppointmentEventPublisher {
    void publish(AppointmentEvent event);
}
