package com.example.doctorappointmentbooking.appointment_booking.internal.application.queries.contracts;

import com.example.doctorappointmentbooking.appointment_booking.internal.application.queries.ViewDoctorAvailableSlotsResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IViewDoctorAvailability {

    CompletableFuture<List<ViewDoctorAvailableSlotsResponse>> getDoctorAvailableTimeSlots();
}
