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
    
    public void loadPrescriptionsFromCsv(String fileName) { /* parse prescriptions.csv */ }
    public void loadReferralsFromCsv(String fileName) { /* parse referrals.csv */ }

    public void createPrescription(String id, String title, String content, String date,
                                   String drug, String dose) {
        // create Prescription object, add to repository, append to prescriptions.txt or .csv
    }

    public void createReferral(String id, String title, String content, String date,
                               String reason, String target, String doctor) {
        // use ReferralManager singleton to manage and write referral text file
    }
    
    public void loadPrescriptionsFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0];
                String date = data[4];
                String drug = data[5];
                String dose = data[6];

                Prescription p = new Prescription(id, "Prescription", "Auto-loaded", date, drug, dose);
                documentRepository.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error loading prescriptions CSV: " + e.getMessage());
        }
        
        public void loadReferralsFromCsv(String fileName) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

                String line;
                br.readLine(); // skip header

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

                    String id = data[0];
                    String date = data[6];
                    String reason = data[8];
                    String target = data[9];
                    String doctor = data[3];

                    Referral r = new Referral(id, "Referral", "Auto-loaded", date, reason, target, doctor);
                    documentRepository.add(r);
                }

            } catch (Exception e) {
                System.out.println("Error loading referrals CSV: " + e.getMessage());
            }
        }

    }


}

