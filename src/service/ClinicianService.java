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
    
    public void loadFromCsv(String fileName) {
        // read CSV, create objects, store in repository
    }
    
    public void loadFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int uid = Integer.parseInt(data[0]);
                String first = data[1];
                String last = data[2];
                String dob = data[3];
                String phone = data[4];
                String email = data[5];
                String employeeId = data[6];
                String licenseId = data[7];

                Clinician c = new Clinician(uid, first, last, dob, phone, email, employeeId, licenseId);
                clinicianRepository.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error loading clinicians CSV: " + e.getMessage());
        }
    }


}

