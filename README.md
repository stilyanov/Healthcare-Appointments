# Healthcare-Appointments

This repository contains the REST API for managing appointments in the healthcare management system.

## Table of Contents
- [About](#about)
- [Features](#features)
- [Technologies](#technologies)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)

## About
The Healthcare-Appointments project provides a RESTful API for handling appointments within the healthcare management system.

## Features
- Create, read, update, and delete appointments
- Secure authentication and authorization
- Integration with the main healthcare system

## Technologies
- **Backend:** Java (Spring Boot)
- **Database:** MySQL (for production)
- **Build Tool:** Gradle

## Usage

1. The API will be available at http://localhost:8081.
2. Use tools like Postman to interact with the API endpoints.

## API Endpoints

- GET /appointments/book/{doctorId}: Retrieve available appointment times for a specific doctor on a given date.
- GET /appointments/user/{userId}: Retrieve all appointments for a specific user.
- GET /appointments/{appointmentId}: Retrieve details of a specific appointment by its ID.
- GET /appointments/patient/{patientId}: Retrieve all appointments for a specific patient.
- GET /appointments/doctor/{doctorId}: Retrieve all appointments for a specific doctor.
- GET /appointments/all: Retrieve all appointments with full information.
- POST /appointments/book/{doctorId}: Book a new appointment with a doctor.
- DELETE /appointments/delete/{id}: Delete an appointment by its ID.
