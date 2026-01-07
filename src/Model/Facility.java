package Model;

public class Facility {
    private String facilityId;
    private String name;
    private String type;
    private String address;
    private String postcode;
    private String phoneNumber;
    private String email;
    private String openingHours;
    private String managerName;
    private String capacity;
    private String specialitiesOffered;

    public Facility(String facilityId, String name, String type, String address, String postcode,
                    String phoneNumber, String email, String openingHours, String managerName,
                    String capacity, String specialitiesOffered) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.capacity = capacity;
        this.specialitiesOffered = specialitiesOffered;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getSpecialitiesOffered() {
        return specialitiesOffered;
    }

    @Override
    public String toString() {
        return facilityId + " - " + name + " (" + type + ")";
    }
}
