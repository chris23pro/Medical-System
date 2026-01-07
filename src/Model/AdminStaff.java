package Model;

public class AdminStaff extends Person {
	private String staffid;
	
	

	//constructor
	 public AdminStaff(String uid, String fName, String lName, String dateOfBirth, String phone, String email, String staffId) {
		 super(uid, fName, lName, dateOfBirth, phone, email);
		 staffid = staffId;
		 
	 }

	 //getter method
	 public String getStaffId() {
	        return staffid;
	    }

	// Method
	    public void createPatient(String nhsNumber, String address) {
	        System.out.println("Admin staff " + staffid + " requests to create patient with NHS number: " + nhsNumber + " and address: " + address);
	        // Actual patient creation logic will be handled externally (e.g., PatientManager)
	    }
	 
	 
	 
	 //Method
	    public void manageAppointments(String appId) {
	        System.out.println("Admin staff " + staffid + " manages appointment with ID: " + appId);
	        // Actual appointment management logic will be handled externally
	    }
	    
	    
	  //Method    
	    public void manageFacilityOperations(String facilityCode) {
	        System.out.println("Admin staff " + staffid + " manages facility operations for facility code: " + facilityCode);
	        // Actual facility management logic will be handled externally
	    }
	    
	    @Override 
	    public String toString() {
	    	return getFullName() + " - " + staffid;
	    }
	    
}
