package Model;


public class Person {
	private int uniqueId;
    private String firstName;
    private String lastName;
    private String dob;          
    private String phoneNo;
    private String contactEmail;
	
	//Constructor
    public Person(int uid, String fName, String lName, String dateOfBirth, String phone, String email) {
        uniqueId = uid;
        firstName = fName;
        lastName = lName;
        dob = dateOfBirth;
        phoneNo = phone;
        contactEmail = email;
    }
	
	
	
	//Method
    public String getFullName() {
        return firstName + " " + lastName;
    }
	

}
