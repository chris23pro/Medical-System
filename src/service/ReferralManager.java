package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Referral;

/**
 * ReferralManager (Singleton)
 * ---------------------------
 * Ensures only ONE instance manages:
 * - Referral queue
 * - Referral creation
 * - Referral audit logging
 * - Writing referral output to file
 */
public class ReferralManager {

    // The ONE instance of this class
    private static ReferralManager instance;

    // Internal referral queue
    private List<Referral> referralQueue = new ArrayList<>();

    /**
     * Private constructor prevents external instantiation.
     */
    private ReferralManager() {}

    /**
     * Global access point to the single instance.
     */
    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    /**
     * Add a referral to the queue.
     */
    public void addReferral(Referral r) {
        referralQueue.add(r);
    }

    /**
     * Returns all referrals currently in the queue.
     */
    public List<Referral> getAllReferrals() {
        return referralQueue;
    }

    /**
     * Writes referral details to a text file (simulated email/EHR update).
     */
    public void writeReferralToFile(Referral r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("referral_output.txt", true))) {

            bw.write("Referral ID: " + r.getDocumentID());
            bw.newLine();
            bw.write("Title: " + r.getTitle());
            bw.newLine();
            bw.write("Date: " + r.getDateCreated());
            bw.newLine();
            bw.write("Reason: " + r.getReferralReason());
            bw.newLine();
            bw.write("Target Specialist: " + r.getTargetSpecialist());
            bw.newLine();
            bw.write("Specialist Doctor: " + r.getSpecialistDoctor());
            bw.newLine();
            bw.write("----------------------------------------");
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error writing referral file: " + e.getMessage());
        }
    }
}
