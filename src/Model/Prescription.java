package Model;

public class Prescription extends ClinicalDocument {
	//attributes 
	private String drugName;
	private String Dosage;
	//contructor class 
	public Prescription(String docID, String docTitle, String docContent, String docDate,String dn,String dg ) {
		super(docID, docTitle, docContent, docDate);
		drugName=dn;
		Dosage= dg;
	}
	
	//getter
	public String getDrugName() {
        return drugName;
    }
	
	public String getDosage() {
        return Dosage;
    }
	
	//methods 
	public String getDrugDetails() {
        return "Drug: " + drugName + ", Dosage: " + Dosage;
    }
	
	@Override 
	public String toString() { 
		return getDocumentID() + " - " + getDrugName() + " " + getDosage();
	}
}
