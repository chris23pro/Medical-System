package Model;

public class Patient extends Person {
	private String nhsNumber;
    private String address;

	
	//constructor initializes Patient
	public Patient(int uid, String fName, String lName, String dateOfBirth, String phone, String email,String nhsNum, String addr) {
		
		// Call Person constructor
		super(uid, fName, lName, dateOfBirth, phone, email);
		
		// Assign Patient-specific attributes
		nhsNumber = nhsNum;
		address = addr;
		}
	
	
	
	public String getNhsNumber() {
        return nhsNumber;
    }
	
	public String getAddress() {
        return address;
    }
	
	
	
	 public void createAppointment(String date, String time) {
        System.out.println("Request to create appointment for patient " + nhsNumber + " on " + date + " at " + time);
        // Actual creation will be handled elsewhere 
    }
	
	 
	 public void modifyAppointment(String appID) {
	        System.out.println("Request to modify appointment with ID " + appID + " for patient " + nhsNumber);
	 
	     // Actual modification logic will be handled externally
	}
	 
	 
	 public void cancelAppointment(String appID){
		 System.out.println("Request to cancel appointment with ID " + appID + " for patient " + nhsNumber);
		
		 // Actual cancellation logic will be handled externally
	 }
	 
	
	 @Override 
	 public String toString() { 
		 
		 return getFullName() + " (" + nhsNumber + ")";
	 }
	
	
	
	
	
	
	
	
	
	
}
