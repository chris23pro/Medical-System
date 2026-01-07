package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import Model.Appointment;
import Model.Patient;
import Model.Clinician;

import repository.AppointmentRepository;
import repository.PatientRepository;
import repository.ClinicianRepository;

public class AppointmentService {

    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private PatientRepository patientRepository = new PatientRepository();
    private ClinicianRepository clinicianRepository = new ClinicianRepository();

    public String bookAppointment(String nhsNumber, String clinicianId, Appointment appointment) {

        Optional<Patient> patient = patientRepository.findByNhs(nhsNumber);
        if (patient.isEmpty()) {
            return "Cannot book appointment. Patient does not exist.";
        }

        Optional<Clinician> clinician = clinicianRepository.findByEmployeeId(clinicianId);
        if (clinician.isEmpty()) {
            return "Cannot book appointment. Clinician does not exist.";
        }

        Optional<Appointment> existing = appointmentRepository.findById(appointment.getAppId());
        if (existing.isPresent()) {
            return "Appointment ID already exists.";
        }

        appointmentRepository.save(appointment);

        return "Appointment booked successfully.";
    }

    public Optional<Appointment> findAppointment(String appId) {
        return appointmentRepository.findById(appId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public String cancelAppointment(String appId) {
        appointmentRepository.deleteById(appId);
        return "Appointment cancelled (if it existed).";
    }

    public void loadFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String appId = data[0];
                String dateTime = data[4] + " " + data[5]; // date + time

                Appointment a = new Appointment(appId, dateTime);
                appointmentRepository.add(a);
            }

        } catch (Exception e) {
            System.out.println("Error loading appointments CSV: " + e.getMessage());
        }
    }
}
