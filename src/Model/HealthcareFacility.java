package Model;

public class HealthcareFacility {
	private String facilityCode;
	
	//constructor
	public HealthcareFacility(String facCode) {
		facilityCode = facCode;
	}

	
	// Getter
	public String getFacilityCode() {
        return facilityCode;
    }
	
	//void methods 
	public void manageResources() {
        System.out.println("Managing resources for facility " + facilityCode);
    }
	
}
