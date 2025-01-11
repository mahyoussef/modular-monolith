package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.events;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.NewAppointmentEvent;
import org.springframework.context.ApplicationEvent;


public class AppointmentConfirmationEvent extends ApplicationEvent {

    public AppointmentConfirmationEvent(NewAppointmentEvent source) {
        super(source);
    }

    @Override
    public String toString() {
        return "ApplicationEvent: New Appointment Saved :: " + this.getSource();
    }
}
