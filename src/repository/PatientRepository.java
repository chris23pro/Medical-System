package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Patient;

/**
 * In-memory implementation of PatientRepository.
 * Uses an ArrayList to simulate a database.
 */
public class PatientRepository {

    /**
     * Internal storage for Patient objects.
     * Acts as a fake database.
     */
    private List<Patient> patients = new ArrayList<>();

    /**
     * Saves a patient into memory.
     */
    public void save(Patient patient) {
        patients.add(patient);
    }

    /**
     * Searches for a patient by NHS number.
     */
    public Optional<Patient> findByNhs(String nhsNumber) {
        for (Patient patient : patients) {
            if (patient.getNhsNumber().equals(nhsNumber)) {
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns a copy of all patients.
     */
    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    /**
     * Deletes a patient by NHS number.
     */
    public void deleteByNhs(String nhsNumber) {
        patients.removeIf(patient -> patient.getNhsNumber().equals(nhsNumber));
    }
}

