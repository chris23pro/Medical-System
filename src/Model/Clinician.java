package Model;

public class Clinician extends Person {

	private String employeeID;
	private String licenseID;
	
	
	
	public Clinician(String uid, String fName, String lName, String dateOfBirth, String phone, String email,
					 String emID, String LicsID) {
		super(uid, fName, lName, dateOfBirth, phone, email);
		employeeID = emID;
		licenseID= LicsID;
		
	}
	
	//getter methods 
	
	public String getEmployeeID() {
	    return employeeID;
	}
	
	public String getLicenseID() {
	    return licenseID;
	}
	
	// void methods 
	
	public void viewEHR() {
	    System.out.println("Clinician " + getEmployeeID() + " is viewing Electronic Health Records.");
	}

	
	public void viewClinicalAssessments() {
	    System.out.println("Clinician " + getEmployeeID() + " is viewing clinical assessments.");
	}
	
	
	@Override 
	public String toString() {
		return getFullName() + " [" + employeeID + "] - " + (licenseID == null ? "" : licenseID); 
	}
}
