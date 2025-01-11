package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.NewAppointmentEvent;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class AppointmentConfirmationEvent extends ApplicationEvent {

    public AppointmentConfirmationEvent(NewAppointmentEvent source) {
        super(source);
    }

    @Override
    public String toString() {
        return "ApplicationEvent: New Appointment Saved :: " + this.getSource();
    }
}
