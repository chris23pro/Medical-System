package Model;


public class SpecialistDoctor extends Clinician {
	//attributes
	private String SPID;

	public SpecialistDoctor(int uid, String fName, String lName, String dateOfBirth, String phone, String email,
			String emID, String LicsID, String specID) {
		super(uid, fName, lName, dateOfBirth, phone, email, emID, LicsID);
		SPID = specID;
	}
	
	
	//getters
	public String getSpecilistID() {
		return SPID;
	}
	
	//void methods
	
	public void createReferral() {
		
	}
	
	
	public void createPrescription() {
		
	}

}
