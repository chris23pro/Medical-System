package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.ClinicalDocument;

public class DocumentRepository {

    private List<ClinicalDocument> documents = new ArrayList<>();

    public void save(ClinicalDocument document) {
        documents.add(document);
    }

    public void add(ClinicalDocument document) {
        save(document);
    }

    public Optional<ClinicalDocument> findById(String documentId) {
        for (ClinicalDocument doc : documents) {
            if (doc.getDocumentID().equals(documentId)) {
                return Optional.of(doc);
            }
        }
        return Optional.empty();
    }

    public List<ClinicalDocument> findAll() {
        return new ArrayList<>(documents);
    }

    public void deleteById(String documentId) {
        documents.removeIf(doc -> doc.getDocumentID().equals(documentId));
    }
}
