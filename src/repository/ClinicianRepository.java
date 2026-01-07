package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Clinician;

public class ClinicianRepository {

    private List<Clinician> clinicians = new ArrayList<>();

    public void save(Clinician clinician) {
        clinicians.add(clinician);
    }

    public void add(Clinician clinician) {
        save(clinician);
    }

    public Optional<Clinician> findByEmployeeId(String employeeId) {
        for (Clinician clinician : clinicians) {
            if (clinician.getEmployeeID().equals(employeeId)) {
                return Optional.of(clinician);
            }
        }
        return Optional.empty();
    }

    public List<Clinician> findAll() {
        return new ArrayList<>(clinicians);
    }

    public void deleteByEmployeeId(String employeeId) {
        clinicians.removeIf(clinician -> clinician.getEmployeeID().equals(employeeId));
    }
}
