package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import repository.PatientRepository;
import Model.Patient;

public class PatientService {

    private PatientRepository patientRepository;

    public PatientService() {
        this(new PatientRepository());
    }

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public String registerPatient(Patient patient) {

        if (patient.getNhsNumber() == null || patient.getNhsNumber().length() < 9 || patient.getNhsNumber().length() > 10) {
            return "Invalid NHS number. It must be 9 or 10 characters long.";
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

    public String updatePatient(String existingNhsNumber, Patient updatedPatient) {
        Optional<Patient> existing = patientRepository.findByNhs(existingNhsNumber);
        if (existing.isEmpty()) {
            return "Patient not found.";
        }

        patientRepository.deleteByNhs(existingNhsNumber);
        patientRepository.save(updatedPatient);
        return "Patient updated successfully.";
    }

    public void loadFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = CsvUtils.parseLine(line);

                if (data.length < 14) {
                    continue;
                }

                String patientId = data[0];
                String first = data[1];
                String last = data[2];
                String dob = data[3];
                String nhs = data[4];
                String gender = data[5];
                String phone = data[6];
                String email = data[7];
                String address = data[8];
                String postcode = data[9];
                String emergencyContactName = data[10];
                String emergencyContactPhone = data[11];
                String registrationDate = data[12];
                String gpSurgeryId = data[13];

                Patient p = new Patient(patientId, first, last, dob, phone, email, nhs, address, gender,
                        postcode, emergencyContactName, emergencyContactPhone, registrationDate, gpSurgeryId);
                patientRepository.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error loading patients CSV: " + e.getMessage());
        }
    }
}
