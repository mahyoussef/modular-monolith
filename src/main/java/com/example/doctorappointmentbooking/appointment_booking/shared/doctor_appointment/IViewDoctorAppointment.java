package com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IViewDoctorAppointment {
    CompletableFuture<List<AppointmentDto>> getUpcomingAppointments();
}
