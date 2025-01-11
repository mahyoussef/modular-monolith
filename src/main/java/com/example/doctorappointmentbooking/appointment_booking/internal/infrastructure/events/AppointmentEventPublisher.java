package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.events;

import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.AppointmentEvent;
import com.example.doctorappointmentbooking.appointment_booking.internal.domain.models.NewAppointmentEvent;
import com.example.doctorappointmentbooking.appointment_booking.shared.AppointmentConfirmationEvent;
import com.example.doctorappointmentbooking.appointment_booking.shared.NewAppointmentEventDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class AppointmentEventPublisher implements IAppointmentEventPublisher, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;;

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(AppointmentEvent appointmentEvent) {
        if (appointmentEvent instanceof NewAppointmentEvent event) {
            NewAppointmentEventDto eventDto =
                    new NewAppointmentEventDto(event.appointmentId(), event.reservedAt(), event.patientName());
            this.applicationEventPublisher.publishEvent(new AppointmentConfirmationEvent(eventDto));
        }
    }
}
