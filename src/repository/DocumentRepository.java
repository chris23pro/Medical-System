package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.ClinicalDocument;

/**
 * In-memory repository for storing ClinicalDocument objects.
 * Uses an ArrayList to simulate a database.
 */
public class DocumentRepository {

    /**
     * Internal storage for documents.
     */
    private List<ClinicalDocument> documents = new ArrayList<>();

    /**
     * Saves a document into memory.
     */
    public void save(ClinicalDocument document) {
        documents.add(document);
    }

    /**
     * Searches for a document by its ID.
     */
    public Optional<ClinicalDocument> findById(String documentId) {
        for (ClinicalDocument doc : documents) {
            if (doc.getDocumentID().equals(documentId)) {
                return Optional.of(doc);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns all stored documents.
     */
    public List<ClinicalDocument> findAll() {
        return new ArrayList<>(documents);
    }

    /**
     * Deletes a document by its ID.
     */
    public void deleteById(String documentId) {
        documents.removeIf(doc -> doc.getDocumentID().equals(documentId));
    }
}

