package Model;

public class Referral extends ClinicalDocument {
    
    private String referralReason;
    private String targetSpecialist;
    private String specialistDoctor;

    public Referral(String docID, String docTitle, String docContent, String docDate,
                    String RR, String tS, String SD) {
        super(docID, docTitle, docContent, docDate);
        referralReason = RR;
        targetSpecialist = tS;
        specialistDoctor = SD;
    }

    // Getters
    public String getReferralReason() {
        return referralReason;
    }

    public String getTargetSpecialist() {
        return targetSpecialist;
    }

    public String getSpecialistDoctor() {
        return specialistDoctor;
    }

    // Methods
    public String getReferralDetails() {
        return "Referral Reason: " + referralReason +
               ", Target Specialist: " + targetSpecialist +
               ", Specialist Doctor: " + specialistDoctor;
    }

    @Override 
    public String toString() {
        return getDocumentID() + " - " + getTargetSpecialist() + " : " + getReferralReason(); 
    }
}
