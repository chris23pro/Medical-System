package Model;

public class Nurse extends Clinician {
	//attributes
	private String nurseId;

	public Nurse(String uid, String fName, String lName, String dateOfBirth, String phone, String email, String emID,
			String LicsID,String nID) {
		super(uid, fName, lName, dateOfBirth, phone, email, emID, LicsID);
		nurseId = nID;
	}
	
	//getters
	public String getNurseID() {
		return nurseId;
	}
	
	//void methods 
	
	public void createReferral() {
		
	}
	
	
	public void viewClinicalAsessments() {
		
	}
	
	@Override 
	public String toString() {
		return "Nurse " + getFullName() + " (" + getNurseID() + ")";
	}
	
}
