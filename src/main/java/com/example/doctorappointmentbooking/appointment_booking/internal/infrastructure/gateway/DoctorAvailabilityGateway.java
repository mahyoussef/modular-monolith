package com.example.doctorappointmentbooking.appointment_booking.internal.infrastructure.gateway;

import com.example.doctorappointmentbooking.appointment_booking.internal.application.queries.contracts.IViewDoctorAvailability;
import com.example.doctorappointmentbooking.appointment_booking.internal.application.queries.ViewDoctorAvailableSlotsResponse;
import com.example.doctorappointmentbooking.doctor_availability.shared.IDoctorAvailability;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorAvailabilityGateway implements IViewDoctorAvailability {
    private final IDoctorAvailability doctorAvailability;

    public DoctorAvailabilityGateway(IDoctorAvailability doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public CompletableFuture<List<ViewDoctorAvailableSlotsResponse>> getDoctorAvailableTimeSlots() {
        return doctorAvailability.getAvailableTimeSlots().thenApply(timeSlotDtos -> timeSlotDtos.stream()
                        .map(timeSlot -> new ViewDoctorAvailableSlotsResponse(timeSlot.id(), timeSlot.date(),
                                timeSlot.doctorId(), timeSlot.doctorName(), timeSlot.cost())).toList()
        );
    }
}
