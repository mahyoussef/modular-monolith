package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.AppointmentEvent;

public interface IAppointmentEventPublisher {
    void publish(AppointmentEvent event);
}
