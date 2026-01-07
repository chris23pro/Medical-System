package Model;

public class GeneralPractitioner extends Clinician {
	//attributes for GP
	private String gpCode;
	
	//constructor 
	public GeneralPractitioner(String uid, String fName, String lName, String dateOfBirth, String phone, String email,
			String emID, String LicsID, String gpC ) {
		super(uid, fName, lName, dateOfBirth, phone, email, emID, LicsID);
		gpCode = gpC;
	}
	
	//getters methods
	public String getGpCode() {
        return gpCode;
    }
	
	//void methods 
	 public void createReferral() {
	        System.out.println("GP " + gpCode + " is creating a referral.");
	    }

	 public void createPrescription() {
	        System.out.println("GP " + gpCode + " is creating a prescription.");
	    }
	 
	 @Override 
	 public String toString() {
		 return "GP " + getFullName() + " (" + getGpCode() + ")"; 
	}
}
