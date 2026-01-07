package Model;


public class SpecialistDoctor extends Clinician {
	//attributes
	private String specialistId;

	public SpecialistDoctor(String uid, String fName, String lName, String dateOfBirth, String phone, String email,
			String emID, String LicsID, String specID) {
		super(uid, fName, lName, dateOfBirth, phone, email, emID, LicsID);
		specialistId = specID;
	}
	
	
	//getters
	public String getSpecialistId() {
		return specialistId;
	}

	public String getSpecilistID() {
		return getSpecialistId();
	}
	
	//void methods
	
	public void createReferral() {
		
	}
	
	
	public void createPrescription() {
		
	}
	
	@Override 
	public String toString() {
		return "Specialist " + getFullName() + " (" + getSpecialistId() + ") - " + getLicenseID(); 
	}
}
