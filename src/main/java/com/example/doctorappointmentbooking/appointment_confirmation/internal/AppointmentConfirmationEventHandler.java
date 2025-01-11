package com.example.doctorappointmentbooking.appointment_confirmation.internal;

import com.example.doctorappointmentbooking.appointment_booking.shared.events.AppointmentConfirmationEvent;
import com.example.doctorappointmentbooking.appointment_booking.shared.events.NewAppointmentEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConfirmationEventHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @EventListener
    void handleAppointmentConfirmationEvent(AppointmentConfirmationEvent event) {
        // TODO: Get the exactly time slot talking to doctor availability module...
        NewAppointmentEventDto appointmentEventDto = (NewAppointmentEventDto) event.getSource();
        log.info("Notifying Patient....");
        log.info("Thanks {} , your appointment is confirmed for Dr.Mahmoud at {} with reference: {}",
                appointmentEventDto.patientName(), appointmentEventDto.reservedAt(), appointmentEventDto.appointmentId());
        log.info("Notifying Doctor...");
        log.info("There is a new appointment with {} at {} with reference: {}",
                appointmentEventDto.patientName(), appointmentEventDto.reservedAt(), appointmentEventDto.appointmentId());
    }
}
