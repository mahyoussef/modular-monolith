package com.example.doctorappointmentbooking.appointment_booking.shared;

import org.springframework.context.ApplicationEvent;


public class AppointmentConfirmationEvent extends ApplicationEvent {

    public AppointmentConfirmationEvent(NewAppointmentEventDto source) {
        super(source);
    }

    @Override
    public String toString() {
        return "ApplicationEvent: New Appointment Saved :: " + this.getSource();
    }
}
