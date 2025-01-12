# Doctor Appointment Booking [![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
Creating a backend system for a doctor appointment booking application. The system will be designed for a specific single doctor and should handle the logic behind managing and booking appointments. The project focuses on implementing the necessary APIs and functionality to meet the business requirements.
Business Requirements:

# Business requirements
Doctor Availability:

- As a doctor, I want to be able to list my slots
- As a doctor, I want to be able to add new slots where a single time slot should have the following:
```
Id: Guid
Time: Date → 22/02/2023 04:30 pm
DoctorId: Guid
DoctorName: string
IsReserved: bool
Cost: Decimal
```
Appointment Booking:

- As a Patient, I want to be able to view all doctors' available (only) slots
- As a Patient, I want to be able to book an appointment on a free slot where. An Appointment should have the following:
```
Id: Guid
SlotId: Guid
PatientId: Guid
PatientName: string
ReservedAt: Date
```
Appointment Confirmation:

Once a patient schedules an appointment, the system should send a confirmation notification to the patient and the doctor
The confirmation notification should include the appointment details, such as the patient's name, appointment time, and Doctor's name.
For the sake of this assessment, the notification could be just a Log message

Doctor Appointment Management:

- As a Doctor, I want to be able to view my upcoming appointments.
- As a Doctor, I want to be able to mark appointments as completed or cancel them if necessary.

# Data Persistence
In-memory list with no db at all

# Specifications
The system is serving a single Doctor only without authentication or authorisation, applying modular monolith architecture.

The system consists of four modules each with a different architecture as follows:

1. Doctor Availability Module: Traditional Layered Architecture
2. Appointment Booking Module: Clean architecture
3. Appointment Confirmation Module: Simplest architecture possible
4. Doctor Appointment Management: Hexagonal Architecture
