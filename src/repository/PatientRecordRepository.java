package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.PatientRecord;

/**
 * In-memory repository for storing PatientRecord objects.
 * Uses an ArrayList to simulate a database.
 */
public class PatientRecordRepository {

    /**
     * Internal storage for patient records.
     */
    private List<PatientRecord> records = new ArrayList<>();

    /**
     * Saves a patient record into memory.
     */
    public void save(PatientRecord record) {
        records.add(record);
    }

    /**
     * Searches for a patient record by its ID.
     */
    public Optional<PatientRecord> findById(String recordId) {
        for (PatientRecord record : records) {
            if (record.getRecordID().equals(recordId)) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns all stored patient records.
     */
    public List<PatientRecord> findAll() {
        return new ArrayList<>(records);
    }

    /**
     * Deletes a patient record by its ID.
     */
    public void deleteById(String recordId) {
        records.removeIf(record -> record.getRecordID().equals(recordId));
    }
}

