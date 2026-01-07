package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Patient;

public class PatientRepository {

    private List<Patient> patients = new ArrayList<>();

    public void save(Patient patient) {
        patients.add(patient);
    }

    public void add(Patient patient) {
        save(patient);
    }

    public Optional<Patient> findByNhs(String nhsNumber) {
        for (Patient patient : patients) {
            if (patient.getNhsNumber().equals(nhsNumber)) {
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }

	public Optional<Patient> findById(String patientId) {
		for (Patient patient : patients) {
			if (patient.getPatientId().equals(patientId)) {
				return Optional.of(patient);
			}
		}
		return Optional.empty();
	}

    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    public void deleteByNhs(String nhsNumber) {
        patients.removeIf(patient -> patient.getNhsNumber().equals(nhsNumber));
    }
}
