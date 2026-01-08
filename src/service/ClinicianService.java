package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import Model.Clinician;
import repository.ClinicianRepository;

public class ClinicianService {

    private ClinicianRepository clinicianRepository;

    public ClinicianService() {
        this(new ClinicianRepository());
    }

    public ClinicianService(ClinicianRepository clinicianRepository) {
        this.clinicianRepository = clinicianRepository;
    }

    public String registerClinician(Clinician clinician) {

        if (clinician.getEmployeeID() == null || clinician.getEmployeeID().isEmpty()) {
            return "Employee ID cannot be empty.";
        }

        Optional<Clinician> existing = clinicianRepository.findByEmployeeId(clinician.getEmployeeID());
        if (existing.isPresent()) {
            return "A clinician with this employee ID already exists.";
        }

        clinicianRepository.save(clinician);

        return "Clinician registered successfully.";
    }

    public Optional<Clinician> findClinician(String employeeId) {
        return clinicianRepository.findByEmployeeId(employeeId);
    }

    public List<Clinician> getAllClinicians() {
        return clinicianRepository.findAll();
    }

    public String deleteClinician(String employeeId) {
        clinicianRepository.deleteByEmployeeId(employeeId);
        return "Clinician deleted (if they existed).";
    }

    public String updateClinician(String existingEmployeeId, Clinician updatedClinician) {
        Optional<Clinician> existing = clinicianRepository.findByEmployeeId(existingEmployeeId);
        if (existing.isEmpty()) {
            return "Clinician not found.";
        }

        clinicianRepository.deleteByEmployeeId(existingEmployeeId);
        clinicianRepository.save(updatedClinician);
        return "Clinician updated successfully.";
    }

    public void loadFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = CsvUtils.parseLine(line);
                if (data.length < 11) {
                    continue;
                }

                String clinicianId = data[0];
                String first = data[1];
                String last = data[2];
                String phone = data[6];
                String email = data[7];
                String employeeId = clinicianId;
                String licenseId = data[5];

                Clinician c = new Clinician(clinicianId, first, last, "", phone, email, employeeId, licenseId);
                clinicianRepository.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error loading clinicians CSV: " + e.getMessage());
        }
    }
}
