package Model;


public class Person {
	private String uniqueId;
    private String firstName;
    private String lastName;
    private String dob;          
    private String phoneNo;
    private String contactEmail;
	
	//Constructor
    public Person(String uid, String fName, String lName, String dateOfBirth, String phone, String email) {
        uniqueId = uid;
        firstName = fName;
        lastName = lName;
        dob = dateOfBirth;
        phoneNo = phone;
        contactEmail = email;
    }
	
	
    //getters
    public String getUniqueId() {
    	return uniqueId;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName; 
    }
    
    public String getFullName() {
    	return firstName + " " + lastName;
    }
    
	
    public String getDob() {
    	return dob;
    }
    
    public String getPhoneNo() {
    	return phoneNo;
    }
    
    public String getContactEmail() {
    	return contactEmail;
    }
    
    
     

}
