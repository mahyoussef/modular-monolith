package com.example.doctorappointmentbooking.doctor_availability.internal.business.models;

import java.time.LocalDateTime;

public record AddTimeSlotRequest(LocalDateTime date,
                                 String doctorId,
                                 String doctorName,
                                 boolean isReserved,
                                 double cost) {
}
