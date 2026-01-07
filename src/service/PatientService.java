package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import repository.PatientRepository;
import Model.Patient;

public class PatientService {

    private PatientRepository patientRepository = new PatientRepository();

    public String registerPatient(Patient patient) {

        if (patient.getNhsNumber() == null || patient.getNhsNumber().length() != 10) {
            return "Invalid NHS number. It must be exactly 10 characters long.";
        }

        Optional<Patient> existing = patientRepository.findByNhs(patient.getNhsNumber());
        if (existing.isPresent()) {
            return "A patient with this NHS number already exists.";
        }

        patientRepository.save(patient);

        return "Patient registered successfully.";
    }

    public Optional<Patient> findPatient(String nhsNumber) {
        return patientRepository.findByNhs(nhsNumber);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public String deletePatient(String nhsNumber) {
        patientRepository.deleteByNhs(nhsNumber);
        return "Patient deleted (if they existed).";
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
