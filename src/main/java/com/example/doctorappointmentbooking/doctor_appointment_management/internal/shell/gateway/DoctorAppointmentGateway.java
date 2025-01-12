package com.example.doctorappointmentbooking.doctor_appointment_management.internal.shell.gateway;

import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.ICancelAppointment;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.ICompleteAppointment;
import com.example.doctorappointmentbooking.appointment_booking.shared.doctor_appointment.IViewDoctorAppointment;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.output_ports.IDoctorAppointmentGateway;
import com.example.doctorappointmentbooking.doctor_appointment_management.internal.core.services.ViewAppointmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DoctorAppointmentGateway implements IDoctorAppointmentGateway {
    private final IViewDoctorAppointment viewDoctorUpcomingAppointments;
    private final ICancelAppointment cancelAppointment;
    private final ICompleteAppointment completeAppointment;

    public DoctorAppointmentGateway(IViewDoctorAppointment viewDoctorUpcomingAppointments,
                                    ICancelAppointment cancelAppointment,
                                    ICompleteAppointment completeAppointment) {
        this.viewDoctorUpcomingAppointments = viewDoctorUpcomingAppointments;
        this.cancelAppointment = cancelAppointment;
        this.completeAppointment = completeAppointment;
    }

    @Override
    public CompletableFuture<List<ViewAppointmentResponse>> getUpcomingAppointments() {
        return viewDoctorUpcomingAppointments.getUpcomingAppointments()
                .thenApply(appointmentDtos -> appointmentDtos.stream().map(appointmentDto -> new ViewAppointmentResponse(appointmentDto.timeSlot(),
                        appointmentDto.patientId(),
                        appointmentDto.patientName(),
                        appointmentDto.reservedAt())).toList());
    }

    public CompletableFuture<Void> cancelAppointment(String appointmentId) {
        return cancelAppointment.cancel(appointmentId);
    }

    public CompletableFuture<Void> completeAppointment(String appointmentId) {
        return completeAppointment.complete(appointmentId);
    }
}
