package Model;

public class Appointment {
	
	private String appId;
	private String patientId;
	private String clinicianId;
	private String facilityId;
	private String dateTime;
	private String appointmentType;
	private String status;
	private String reasonForVisit;

	//Constructor
	public Appointment(String id,String dt) {
		appId= id;
	    dateTime= dt;
	}

	public Appointment(String id, String patientId, String clinicianId, String facilityId,
					   String date, String time, String appointmentType, String status, String reasonForVisit) {
		this.appId = id;
		this.patientId = patientId;
		this.clinicianId = clinicianId;
		this.facilityId = facilityId;
		this.dateTime = date + " " + time;
		this.appointmentType = appointmentType;
		this.status = status;
		this.reasonForVisit = reasonForVisit;
	}
	
	
	// Getters
    public String getAppId() {
        return appId;
    }

    public String getDateTime() {
        return dateTime;
    }

	public String getPatientId() {
		return patientId;
	}

	public String getClinicianId() {
		return clinicianId;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public String getStatus() {
		return status;
	}

	public String getReasonForVisit() {
		return reasonForVisit;
	}
	
	
	
	//methods 
	public void checkAvailability() {
        System.out.println("Checking availability for appointment " + appId + " at " + dateTime);
    }
	
	
	public void modifyTime(String newDateTime) {
        dateTime = newDateTime;
        System.out.println("Appointment " + appId + " time updated to " + dateTime);
    }
	
	
	
	@Override
	public String toString() {
		return appId + " : " + dateTime;
	}
	





















}
