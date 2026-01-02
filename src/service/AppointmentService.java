package service;

// Importing required classes
import java.util.List;
import java.util.Optional;

import Model.Appointment;
import Model.Patient;
import Model.Clinician;

import repository.AppointmentRepository;
import repository.PatientRepository;
import repository.ClinicianRepository;

/**
 * Service layer responsible for handling appointment-related business logic.
 * Ensures patients and clinicians exist before booking appointments.
 */
public class AppointmentService {

    // Repositories used by this service
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private PatientRepository patientRepository = new PatientRepository();
    private ClinicianRepository clinicianRepository = new ClinicianRepository();

    /**
     * Books a new appointment after performing validation checks.
     */
    public String bookAppointment(String nhsNumber, String clinicianId, Appointment appointment) {

        // Check if patient exists
        Optional<Patient> patient = patientRepository.findByNhs(nhsNumber);
        if (patient.isEmpty()) {
            return "Cannot book appointment. Patient does not exist.";
        }

        // Check if clinician exists
        Optional<Clinician> clinician = clinicianRepository.findByEmployeeId(clinicianId);
        if (clinician.isEmpty()) {
            return "Cannot book appointment. Clinician does not exist.";
        }

        // Check if appointment ID already exists
        Optional<Appointment> existing = appointmentRepository.findById(appointment.getAppId());
        if (existing.isPresent()) {
            return "Appointment ID already exists.";
        }

        // Save the appointment
        appointmentRepository.save(appointment);

        return "Appointment booked successfully.";
    }

    /**
     * Finds an appointment by its ID.
     */
    public Optional<Appointment> findAppointment(String appId) {
        return appointmentRepository.findById(appId);
    }

    /**
     * Returns all appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Cancels an appointment using its ID.
     */
    public String cancelAppointment(String appId) {
        appointmentRepository.deleteById(appId);
        return "Appointment cancelled (if it existed).";
    }
}

