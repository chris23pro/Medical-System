package Model;

public class GeneralPractitioner extends Clinician {
	//attributes for GP
	private String gpcode;
	
	//constructor 
	public GeneralPractitioner(int uid, String fName, String lName, String dateOfBirth, String phone, String email,
			String emID, String LicsID, String gpC ) {
		super(uid, fName, lName, dateOfBirth, phone, email, emID, LicsID);
		gpcode = gpC;
	}
	
	//getters methods
	public String getGpCode() {
        return gpcode;
    }
	
	//void methods 
	 public void createReferral() {
	        System.out.println("GP " + gpcode + " is creating a referral.");
	    }

	 public void createPrescription() {
	        System.out.println("GP " + gpcode + " is creating a prescription.");
	    }
}
