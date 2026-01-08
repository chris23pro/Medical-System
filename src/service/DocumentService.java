package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import Model.ClinicalDocument;
import Model.Prescription;
import Model.Referral;
import repository.DocumentRepository;

public class DocumentService {

    private DocumentRepository documentRepository = new DocumentRepository();

    public String saveDocument(ClinicalDocument doc) {

        if (doc.getDocumentID() == null || doc.getDocumentID().isEmpty()) {
            return "Document ID cannot be empty.";
        }

        Optional<ClinicalDocument> existing = documentRepository.findById(doc.getDocumentID());
        if (existing.isPresent()) {
            return "A document with this ID already exists.";
        }

        documentRepository.save(doc);

        return "Document saved successfully.";
    }

    public List<ClinicalDocument> getAllDocuments() {
        return documentRepository.findAll();
    }

    public void loadPrescriptionsFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = CsvUtils.parseLine(line);
                if (data.length < 15) {
                    continue;
                }

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
    }

    public void loadReferralsFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = CsvUtils.parseLine(line);
                if (data.length < 16) {
                    continue;
                }

                String id = data[0];
                String date = data[6];
                String reason = data[8];
                String target = data[3];
                String doctor = data[2];

                Referral r = new Referral(id, "Referral", "Auto-loaded", date, reason, target, doctor);
                documentRepository.add(r);
            }

        } catch (Exception e) {
            System.out.println("Error loading referrals CSV: " + e.getMessage());
        }
    }

    public void createPrescription(String id, String title, String content, String date,
                                   String drug, String dose) {
        Prescription p = new Prescription(id, title, content, date, drug, dose);
        documentRepository.save(p);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prescriptions_output.txt", true))) {

            bw.write("Prescription ID: " + p.getDocumentID());
            bw.newLine();
            bw.write("Title: " + p.getTitle());
            bw.newLine();
            bw.write("Date: " + p.getDateCreated());
            bw.newLine();
            bw.write("Drug: " + p.getDrugName());
            bw.newLine();
            bw.write("Dosage: " + p.getDosage());
            bw.newLine();
            bw.write("----------------------------------------");
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error writing prescription file: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prescriptions.csv", true))) {
            bw.write(String.join(",",
                    id,
                    "",
                    "",
                    "",
                    date,
                    drug,
                    dose,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    date,
                    ""
            ));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing prescription CSV: " + e.getMessage());
        }
    }

    public void createReferral(String id, String title, String content, String date,
                               String reason, String target, String doctor) {
        Referral r = new Referral(id, title, content, date, reason, target, doctor);
        documentRepository.save(r);

        ReferralManager manager = ReferralManager.getInstance();
        manager.addReferral(r);
        manager.writeReferralToFile(r);
    }

    public String updatePrescription(String existingId, String title, String content, String date,
                                     String drug, String dose) {
        Optional<ClinicalDocument> existing = documentRepository.findById(existingId);
        if (existing.isEmpty()) {
            return "Prescription not found.";
        }

        documentRepository.deleteById(existingId);
        Prescription p = new Prescription(existingId, title, content, date, drug, dose);
        documentRepository.save(p);
        return "Prescription updated successfully.";
    }

    public String updateReferral(String existingId, String title, String content, String date,
                                 String reason, String target, String doctor) {
        Optional<ClinicalDocument> existing = documentRepository.findById(existingId);
        if (existing.isEmpty()) {
            return "Referral not found.";
        }

        documentRepository.deleteById(existingId);
        Referral r = new Referral(existingId, title, content, date, reason, target, doctor);
        documentRepository.save(r);
        return "Referral updated successfully.";
    }

    public String deleteDocument(String documentId) {
        documentRepository.deleteById(documentId);
        return "Document deleted (if it existed).";
    }
}
