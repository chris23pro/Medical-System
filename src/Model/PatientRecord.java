package Model;

public class PatientRecord {
	private String recordID;
	//relationship between patient and patient record 
	private Patient patient; 
	
	
	//constructor 
	public PatientRecord(String recID, Patient p) {
        recordID = recID;
        patient = p;
    }
	
	
	//getters
	public String getRecordID() {
        return recordID;
    }
	
	
	public Patient getPatient() {
        return patient;
    }
	
	
	//void methods
	public void addClinicalDocument(String docDetails) {
        System.out.println("Adding clinical document to record " + recordID +
                           " for patient " + patient.getNhsNumber() + ": " + docDetails);
        // Later: connect to ClinicalDocument class and store documents
    }
	
	
	public void getClinicalHistory() {
        System.out.println("Fetching clinical history for patient " + patient.getNhsNumber() +
                           " in record " + recordID);
        // Later: return or print actual history
    }
	
	
	
	

}
