package com.example.doctorappointmentbooking.doctor_availability.internal.presentation;

import com.example.doctorappointmentbooking.doctor_availability.internal.business.DoctorAvailabilityService;
import com.example.doctorappointmentbooking.doctor_availability.internal.business.models.AddTimeSlotRequest;
import com.example.doctorappointmentbooking.doctor_availability.internal.business.models.TimeSlotAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService doctorAvailabilityService;

    public DoctorAvailabilityController(DoctorAvailabilityService doctorAvailabilityService) {
        this.doctorAvailabilityService = doctorAvailabilityService;
    }

    @GetMapping("/v1/time-slots")
    public DeferredResult<ResponseEntity<?>> getAllTimeSlots() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        doctorAvailabilityService.getAllTimeSlots()
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.ok().body(result)));
        return deferredResult;
    }

    @PostMapping("/v1/time-slots")
    public DeferredResult<ResponseEntity<?>> addTimeSlot(@RequestBody AddTimeSlotRequest timeSlotRequest) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        // TODO: Refactoring in-case this kind of pattern get repeated to have more of a generic approach
        doctorAvailabilityService.addTimeSlot(timeSlotRequest)
                .thenAccept(result -> deferredResult.setResult(ResponseEntity.status(HttpStatus.CREATED).body(result)))
                .exceptionally(ex -> {
                    if (ex.getCause() instanceof TimeSlotAlreadyExistsException) {
                        deferredResult.setResult(ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(new ErrorResponse(ex.getCause().getMessage())));
                    }
                    System.out.println(ex);
                    deferredResult.setResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ErrorResponse("An unexpected error occurred")));
                    return null;
                });
        return deferredResult;
    }
}
