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

/**
 * Controller layer that connects the View (UI) with the Service layer.
 * It receives requests from the UI and delegates them to the appropriate service.
 */
public class MedicalController {

    // Service layer objects
    private PatientService patientService = new PatientService();
    private AppointmentService appointmentService = new AppointmentService();
    private ClinicianService clinicianService = new ClinicianService();
    private DocumentService documentService = new DocumentService();
    private PatientRecordService patientRecordService = new PatientRecordService();


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
    public String bookAppointment(String nhsNumber, String clinicianId, Appointment appointment) {
        return appointmentService.bookAppointment(nhsNumber, clinicianId, appointment);
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


    // ---------------------------------------------------------
    // PATIENT RECORD METHODS
    // ---------------------------------------------------------

    /**
     * Creates a patient record.
     */
    public String createRecord(String recordId, String nhsNumber) {
        return patientRecordService.createRecord(recordId, nhsNumber);
    }

    /**
     * Adds a document to a patient record.
     */
    public String addDocumentToRecord(String recordId, ClinicalDocument doc) {
        return patientRecordService.addDocumentToRecord(recordId, doc);
    }
}
