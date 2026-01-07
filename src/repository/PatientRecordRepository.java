package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.PatientRecord;

public class PatientRecordRepository {

    private List<PatientRecord> records = new ArrayList<>();

    public void save(PatientRecord record) {
        records.add(record);
    }

    public Optional<PatientRecord> findById(String recordId) {
        for (PatientRecord record : records) {
            if (record.getRecordID().equals(recordId)) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    public List<PatientRecord> findAll() {
        return new ArrayList<>(records);
    }

    public void deleteById(String recordId) {
        records.removeIf(record -> record.getRecordID().equals(recordId));
    }
}
