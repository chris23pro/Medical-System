package service;

// Importing required classes
import java.util.List;
import java.util.Optional;

import Model.Clinician;
import repository.ClinicianRepository;

/**
 * Service layer responsible for clinician-related business logic.
 * Validates IDs and prevents duplicate registrations.
 */
public class ClinicianService {

    // Repository for storing clinicians
    private ClinicianRepository clinicianRepository = new ClinicianRepository();

    /**
     * Registers a clinician after validation.
     */
    public String registerClinician(Clinician clinician) {

        // Validate employee ID
        if (clinician.getEmployeeID() == null || clinician.getEmployeeID().isEmpty()) {
            return "Employee ID cannot be empty.";
        }

        // Check for duplicates
        Optional<Clinician> existing = clinicianRepository.findByEmployeeId(clinician.getEmployeeID());
        if (existing.isPresent()) {
            return "A clinician with this employee ID already exists.";
        }

        // Save clinician
        clinicianRepository.save(clinician);

        return "Clinician registered successfully.";
    }

    /**
     * Searches for a clinician using their employee ID.
     */
    public Optional<Clinician> findClinician(String employeeId) {
        return clinicianRepository.findByEmployeeId(employeeId);
    }

    /**
     * Returns all clinicians.
     */
    public List<Clinician> getAllClinicians() {
        return clinicianRepository.findAll();
    }
}

