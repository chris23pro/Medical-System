package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Clinician;

/**
 * In-memory implementation of ClinicianRepository.
 * Uses an ArrayList to simulate a database.
 */
public class ClinicianRepository {

    /**
     * Internal storage for clinician objects.
     */
    private List<Clinician> clinicians = new ArrayList<>();

    /**
     * Saves a clinician into memory.
     */
    public void save(Clinician clinician) {
        clinicians.add(clinician);
    }

    /**
     * Searches for a clinician by employee ID.
     */
    public Optional<Clinician> findByEmployeeId(String employeeId) {
        for (Clinician clinician : clinicians) {
            if (clinician.getEmployeeID().equals(employeeId)) {
                return Optional.of(clinician);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns all clinicians.
     */
    public List<Clinician> findAll() {
        return new ArrayList<>(clinicians);
    }

    /**
     * Deletes a clinician by employee ID.
     */
    public void deleteByEmployeeId(String employeeId) {
        clinicians.removeIf(clinician -> clinician.getEmployeeID().equals(employeeId));
    }
}
