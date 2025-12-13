package Model;

public class Appointment {
	
	private String appId;
	private String dateTime;

	//Constructor
	public Appointment(String id,String dt) {
		appId= id;
	    dateTime= dt;
	}
	
	
	// Getters
    public String getAppId() {
        return appId;
    }

    public String getDateTime() {
        return dateTime;
    }
	
	
	
	//methods 
	public void checkAvailability() {
        System.out.println("Checking availability for appointment " + appId + " at " + dateTime);
    }
	
	
	public void modifyTime(String newDateTime) {
        dateTime = newDateTime;
        System.out.println("Appointment " + appId + " time updated to " + dateTime);
    }
	
	
	
	
	





















}
