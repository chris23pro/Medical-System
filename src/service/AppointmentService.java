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

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private ClinicianRepository clinicianRepository;

    public AppointmentService() {
        this(new AppointmentRepository(), new PatientRepository(), new ClinicianRepository());
    }

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                              ClinicianRepository clinicianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.clinicianRepository = clinicianRepository;
    }

    public String bookAppointment(String patientId, String clinicianId, Appointment appointment) {

        Optional<Patient> patient = patientRepository.findById(patientId);
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
                String[] data = CsvUtils.parseLine(line);
                if (data.length < 10) {
                    continue;
                }

                String appId = data[0];
                String patientId = data[1];
                String clinicianId = data[2];
                String facilityId = data[3];
                String date = data[4];
                String time = data[5];
                String appointmentType = data[7];
                String status = data[8];
                String reason = data[9];

                Appointment a = new Appointment(appId, patientId, clinicianId, facilityId, date, time,
                        appointmentType, status, reason);
                appointmentRepository.add(a);
            }

        } catch (Exception e) {
            System.out.println("Error loading appointments CSV: " + e.getMessage());
        }
    }
}
