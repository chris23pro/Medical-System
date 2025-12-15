package Model;

public class Nurse extends Clinician {
	//attributes
	private String nurseID;

	public Nurse(int uid, String fName, String lName, String dateOfBirth, String phone, String email, String emID,
			String LicsID,String nID) {
		super(uid, fName, lName, dateOfBirth, phone, email, emID, LicsID);
		nurseID = nID;
	}
	
	//getters
	public String getNurseID() {
		return nurseID;
	}
	
	//void methods 
	
	public void createReferral() {
		
	}
	
	
	public void viewClinicalAsessments() {
		
	}
	
}
