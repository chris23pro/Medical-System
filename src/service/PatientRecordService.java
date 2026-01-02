package service;

// Importing required classes
import java.util.Optional;

import Model.Patient;
import Model.PatientRecord;
import Model.ClinicalDocument;

import repository.PatientRepository;
import repository.PatientRecordRepository;

/**
 * Service layer responsible for managing patient records.
 * Links documents to records and ensures patients exist.
 */
public class PatientRecordService {

    // Repositories used by this service
    private PatientRecordRepository recordRepository = new PatientRecordRepository();
    private PatientRepository patientRepository = new PatientRepository();

    /**
     * Creates a new patient record after validation.
     */
    public String createRecord(String recordId, String nhsNumber) {

        // Check if patient exists
        Optional<Patient> patient = patientRepository.findByNhs(nhsNumber);
        if (patient.isEmpty()) {
            return "Cannot create record. Patient does not exist.";
        }

        // Check if record ID already exists
        Optional<PatientRecord> existing = recordRepository.findById(recordId);
        if (existing.isPresent()) {
            return "Record ID already exists.";
        }

        // Create and save the record
        PatientRecord record = new PatientRecord(recordId, patient.get());
        recordRepository.save(record);

        return "Patient record created successfully.";
    }

    /**
     * Adds a clinical document to an existing patient record.
     */
    public String addDocumentToRecord(String recordId, ClinicalDocument doc) {

        // Check if record exists
        Optional<PatientRecord> record = recordRepository.findById(recordId);
        if (record.isEmpty()) {
            return "Record not found.";
        }

        // Add document summary to the record
        record.get().addClinicalDocument(doc.getSummary());

        return "Document added to record.";
    }
}

