package com.example.doctorappointmentbooking.doctor_availability.internal.business.models;

public class TimeSlotAlreadyExistsException extends RuntimeException {
    public TimeSlotAlreadyExistsException(String message) {
        super(message);
    }
}
