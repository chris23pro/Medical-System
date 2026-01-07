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
                String nhs = data[6];
                String address = data[7];

                Patient p = new Patient(uid, first, last, dob, phone, email, nhs, address);
                patientRepository.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error loading patients CSV: " + e.getMessage());
        }
    }


}

