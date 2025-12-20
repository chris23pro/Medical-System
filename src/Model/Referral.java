package Model;

public class Referral extends ClinicalDocument {
	
	private String referralReason;
	private String targetSpecialist;
	private String SpecialistDoctor;

	public Referral(String docID, String docTitle, String docContent, String docDate, String RR,String tS,String SD) {
		super(docID, docTitle, docContent, docDate);
		referralReason = RR;
		targetSpecialist = tS;
		SpecialistDoctor = SD;
	}
	
	
	//Getter
	public String getReferralReason() {
        return referralReason;
    }

    public String getTargetSpecialist() {
        return targetSpecialist;
    }

    public String getSpecialistDoctor() {
        return SpecialistDoctor;
    }
    
    
    //methods 
    public String getReferralDetails() {
        return "Referral Reason: " + referralReason +
               ", Target Specialist: " + targetSpecialist +
               ", Specialist Doctor: " + SpecialistDoctor;
    }
    
    @Override 
    public String toString() {
    	return getDocumentID() + " - " + getTargetSpecialist() + " : " + getReferralReason(); 
    }
}
