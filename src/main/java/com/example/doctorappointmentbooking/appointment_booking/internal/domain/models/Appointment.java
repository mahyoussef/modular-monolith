package com.example.doctorappointmentbooking.appointment_booking.internal.domain.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Appointment {
    private final String id;
    private final String slotId;
    private final Patient patient;
    private final LocalDateTime reservedAt;
    private final AppointmentStatus appointmentStatus;
    private final List<AppointmentEvent> occurredEvents = new ArrayList<>();

    private Appointment(String id,
                        String slotId,
                        Patient patient,
                        String reservedAt,
                        AppointmentStatus appointmentStatus) {
        this.id = id;
        this.slotId = slotId;
        this.patient = patient;
        this.reservedAt = ofDate(reservedAt);
        this.appointmentStatus = appointmentStatus;
    }

    public static Appointment of(String appointmentId,
                                 String slotId,
                                 Patient patient,
                                 String reservedAt,
                                 AppointmentStatus appointmentStatus
                                 ) {
        return new Appointment(appointmentId,
                slotId,
                patient,
                reservedAt,
                appointmentStatus);
    }

    public static Appointment newAppointment(String slotId,
                                             Patient patient,
                                             String reservedAt) {
        String appointmentId = UUID.randomUUID().toString();
        Appointment appointment = new Appointment(appointmentId,
                slotId,
                patient,
                reservedAt,
                AppointmentStatus.NEW);
        appointment.occurredEvents.add(NewAppointmentEvent.of(appointment));
        return appointment;
    }

    public static Appointment cancelAppointment(String appointmentId,
                                                String slotId,
                                                Patient patient,
                                                String reservedAt) {
        Appointment appointment = new Appointment(appointmentId,
                slotId,
                patient,
                reservedAt,
                AppointmentStatus.CANCELLED);
        appointment.occurredEvents.add(CancelAppointmentEvent.of(appointment));
        return appointment;
    }

    public static Appointment completeAppointment(String appointmentId,
                                                String slotId,
                                                Patient patient,
                                                String reservedAt) {
        Appointment appointment = new Appointment(appointmentId,
                slotId,
                patient,
                reservedAt,
                AppointmentStatus.COMPLETED);
        appointment.occurredEvents.add(CompleteAppointmentEvent.of(appointment));
        return appointment;
    }

    private LocalDateTime ofDate(String reservedAt) {
        LocalDateTime date = LocalDateTime.parse(reservedAt);
        if (LocalDateTime.now().isAfter(date)) {
            throw new IllegalArgumentException("Reservation date is too far in the past.");
        }
        return date;
    }

    public List<AppointmentEvent> getOccurredEvents() {
        var events = new ArrayList<>(this.occurredEvents);
        this.occurredEvents.clear();
        return events;
    }

    public String getId() {
        return id;
    }

    public String getSlotId() {
        return slotId;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }
}
