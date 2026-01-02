package service;

// Importing required classes
import java.util.List;
import java.util.Optional;
import repository.PatientRepository;
import Model.Patient;

/**
 * Service layer responsible for handling business logic related to Patients.
 * This includes validation, duplicate checking, and coordinating with the repository.
 */
public class PatientService {

    // Creating an instance of the PatientRepository to access stored patients
    private PatientRepository patientRepository = new PatientRepository();

    /**
     * Registers a new patient after performing validation checks.
     */
    public String registerPatient(Patient patient) {

        // Validate NHS number length
        if (patient.getNhsNumber() == null || patient.getNhsNumber().length() != 10) {
            return "Invalid NHS number. It must be exactly 10 characters long.";
        }

        // Check if a patient with the same NHS number already exists
        Optional<Patient> existing = patientRepository.findByNhs(patient.getNhsNumber());
        if (existing.isPresent()) {
            return "A patient with this NHS number already exists.";
        }

        // Save the patient into the repository
        patientRepository.save(patient);

        // Return success message
        return "Patient registered successfully.";
    }

    /**
     * Searches for a patient using their NHS number.
     */
    public Optional<Patient> findPatient(String nhsNumber) {
        return patientRepository.findByNhs(nhsNumber);
    }

    /**
     * Returns a list of all registered patients.
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Deletes a patient using their NHS number.
     */
    public String deletePatient(String nhsNumber) {
        patientRepository.deleteByNhs(nhsNumber);
        return "Patient deleted (if they existed).";
    }
}

