package Model;

public class Patient extends Person {
	private String patientId;
	private String nhsNumber;
    private String address;
	private String gender;
	private String postcode;
	private String emergencyContactName;
	private String emergencyContactPhone;
	private String registrationDate;
	private String gpSurgeryId;

	
	//constructor initializes Patient
	public Patient(String id, String fName, String lName, String dateOfBirth, String phone, String email,
				   String nhsNum, String addr, String gender, String postcode,
				   String emergencyContactName, String emergencyContactPhone,
				   String registrationDate, String gpSurgeryId) {
		
		// Call Person constructor
		super(id, fName, lName, dateOfBirth, phone, email);
		
		// Assign Patient-specific attributes
		patientId = id;
		nhsNumber = nhsNum;
		address = addr;
		this.gender = gender;
		this.postcode = postcode;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactPhone = emergencyContactPhone;
		this.registrationDate = registrationDate;
		this.gpSurgeryId = gpSurgeryId;
		}
	
	
	
	public String getPatientId() {
		return patientId;
	}

	public String getNhsNumber() {
        return nhsNumber;
    }
	
	public String getAddress() {
        return address;
    }

	public String getGender() {
		return gender;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public String getGpSurgeryId() {
		return gpSurgeryId;
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
		 
		 return getFullName() + " (" + patientId + ")";
	 }
	
	
	
	
	
	
	
	
	
	
	}
