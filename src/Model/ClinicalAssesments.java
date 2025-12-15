package Model;

public class ClinicalAssesments extends ClinicalDocument {
	private String symptoms;
	private String findings;

	public ClinicalAssesments(String docID, String docTitle, String docContent, String docDate,String syp,String fgs ) {
		super(docID, docTitle, docContent, docDate);
		symptoms = syp;
		findings = fgs;
	}
	
	
	//getters
	public String getsymptoms() {
        return symptoms;
    }
	
	public String getfindings() {
        return findings;
    }
	

	//methods 
	public String getAssessmentDetails() {
        return "Symptoms: " + symptoms + ", Findings: " + findings;
    }
}
