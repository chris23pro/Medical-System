package service;

// Importing required classes
import java.util.List;
import java.util.Optional;

import Model.ClinicalDocument;
import repository.DocumentRepository;

/**
 * Service layer responsible for handling clinical documents.
 * Ensures document IDs are unique and valid before saving.
 */
public class DocumentService {

    // Repository for storing documents
    private DocumentRepository documentRepository = new DocumentRepository();

    /**
     * Saves a clinical document after validation.
     */
    public String saveDocument(ClinicalDocument doc) {

        // Validate document ID
        if (doc.getDocumentID() == null || doc.getDocumentID().isEmpty()) {
            return "Document ID cannot be empty.";
        }

        // Check for duplicates
        Optional<ClinicalDocument> existing = documentRepository.findById(doc.getDocumentID());
        if (existing.isPresent()) {
            return "A document with this ID already exists.";
        }

        // Save document
        documentRepository.save(doc);

        return "Document saved successfully.";
    }

    /**
     * Returns all documents.
     */
    public List<ClinicalDocument> getAllDocuments() {
        return documentRepository.findAll();
    }
}

