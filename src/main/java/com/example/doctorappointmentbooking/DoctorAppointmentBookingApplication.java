package com.example.doctorappointmentbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootApplication
public class DoctorAppointmentBookingApplication {

    public static void main(String[] args) {
        ApplicationModules.of(DoctorAppointmentBookingApplication.class).verify();
        SpringApplication.run(DoctorAppointmentBookingApplication.class, args);
    }

}
