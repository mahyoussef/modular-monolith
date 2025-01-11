package com.example.doctorappointmentbooking.appointment_booking.internal.application.queries;

import com.example.doctorappointmentbooking.appointment_booking.internal.application.queries.contracts.IViewDoctorAvailability;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ViewDoctorAvailableSlotsHandler {

    private final IViewDoctorAvailability viewDoctorAvailability;

    public ViewDoctorAvailableSlotsHandler(IViewDoctorAvailability viewDoctorAvailability) {
        this.viewDoctorAvailability = viewDoctorAvailability;
    }

    public CompletableFuture<List<ViewDoctorAvailableSlotsResponse>> handle() {
        return viewDoctorAvailability.getDoctorAvailableTimeSlots();
    }
}
