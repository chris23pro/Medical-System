package controller;

import java.util.List;
import java.util.Optional;

import Model.Patient;
import Model.Appointment;
import Model.Clinician;
import Model.ClinicalDocument;

import service.PatientService;
import service.AppointmentService;
import service.ClinicianService;
import service.DocumentService;
import service.PatientRecordService;
import service.FacilityService;
import service.StaffService;
import repository.AppointmentRepository;
import repository.ClinicianRepository;
import repository.FacilityRepository;
import repository.PatientRecordRepository;
import repository.PatientRepository;
import repository.StaffRepository;
import Model.Facility;
import Model.Staff;

/**
 * Controller layer that connects the View (UI) with the Service layer.
 * It receives requests from the UI and delegates them to the appropriate service.
 */
public class MedicalController {

    // Service layer objects
    private PatientRepository patientRepository = new PatientRepository();
    private ClinicianRepository clinicianRepository = new ClinicianRepository();
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private PatientRecordRepository patientRecordRepository = new PatientRecordRepository();
    private FacilityRepository facilityRepository = new FacilityRepository();
    private StaffRepository staffRepository = new StaffRepository();

    private PatientService patientService = new PatientService(patientRepository);
    private AppointmentService appointmentService = new AppointmentService(appointmentRepository, patientRepository, clinicianRepository);
    private ClinicianService clinicianService = new ClinicianService(clinicianRepository);
    private DocumentService documentService = new DocumentService();
    private PatientRecordService patientRecordService = new PatientRecordService(patientRecordRepository, patientRepository);
    private FacilityService facilityService = new FacilityService(facilityRepository);
    private StaffService staffService = new StaffService(staffRepository);


    // ---------------------------------------------------------
    // PATIENT METHODS
    // ---------------------------------------------------------

    /**
     * Registers a patient by passing the object to the service layer.
     */
    public String registerPatient(Patient patient) {
        return patientService.registerPatient(patient);
    }

    /**
     * Finds a patient using their NHS number.
     */
    public Optional<Patient> findPatient(String nhsNumber) {
        return patientService.findPatient(nhsNumber);
    }

    /**
     * Returns all patients.
     */
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Deletes a patient using their NHS number.
     */
    public String deletePatient(String nhsNumber) {
        return patientService.deletePatient(nhsNumber);
    }


    // ---------------------------------------------------------
    // APPOINTMENT METHODS
    // ---------------------------------------------------------

    /**
     * Books an appointment by delegating to the service layer.
     */
    public String bookAppointment(String patientId, String clinicianId, Appointment appointment) {
        return appointmentService.bookAppointment(patientId, clinicianId, appointment);
    }

    /**
     * Finds an appointment by ID.
     */
    public Optional<Appointment> findAppointment(String appId) {
        return appointmentService.findAppointment(appId);
    }

    /**
     * Returns all appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    /**
     * Cancels an appointment.
     */
    public String cancelAppointment(String appId) {
        return appointmentService.cancelAppointment(appId);
    }


    // ---------------------------------------------------------
    // CLINICIAN METHODS
    // ---------------------------------------------------------

    /**
     * Registers a clinician.
     */
    public String registerClinician(Clinician clinician) {
        return clinicianService.registerClinician(clinician);
    }

    /**
     * Finds a clinician by employee ID.
     */
    public Optional<Clinician> findClinician(String employeeId) {
        return clinicianService.findClinician(employeeId);
    }

    /**
     * Returns all clinicians.
     */
    public List<Clinician> getAllClinicians() {
        return clinicianService.getAllClinicians();
    }


    // ---------------------------------------------------------
    // DOCUMENT METHODS
    // ---------------------------------------------------------

    /**
     * Saves a clinical document.
     */
    public String saveDocument(ClinicalDocument doc) {
        return documentService.saveDocument(doc);
    }

    /**
     * Returns all documents.
     */
    public List<ClinicalDocument> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }


    // ---------------------------------------------------------
    // PATIENT RECORD METHODS
    // ---------------------------------------------------------

    /**
     * Creates a patient record.
     */
    public String createRecord(String recordId, String patientId) {
        return patientRecordService.createRecord(recordId, patientId);
    }

    /**
     * Adds a document to a patient record.
     */
    public String addDocumentToRecord(String recordId, ClinicalDocument doc) {
        return patientRecordService.addDocumentToRecord(recordId, doc);
    }
    
 // CSV loading
    public void loadPatientsFromCsv(String fileName) { patientService.loadFromCsv(fileName); }
    public void loadCliniciansFromCsv(String fileName) { clinicianService.loadFromCsv(fileName); }
    public void loadAppointmentsFromCsv(String fileName) { appointmentService.loadFromCsv(fileName); }
    public void loadPrescriptionsFromCsv(String fileName) { documentService.loadPrescriptionsFromCsv(fileName); }
    public void loadReferralsFromCsv(String fileName) { documentService.loadReferralsFromCsv(fileName); }
    public void loadFacilitiesFromCsv(String fileName) { facilityService.loadFromCsv(fileName); }
    public void loadStaffFromCsv(String fileName) { staffService.loadFromCsv(fileName); }

    // Create prescription (and write to file)
    public void createPrescription(String id, String title, String content, String date,
                                   String drug, String dose) {
        documentService.createPrescription(id, title, content, date, drug, dose);
    }

    // Create referral via Singleton manager (and write to file)
    public void createReferral(String id, String title, String content, String date,
                               String reason, String target, String doctor) {
        documentService.createReferral(id, title, content, date, reason, target, doctor);
    }

}
