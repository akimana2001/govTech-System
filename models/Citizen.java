package models;

public class Citizen {

    private String citizenId;
    private String name;
    private String phone;

    public Citizen(String citizenId, String name, String phone) {
        this.name = name;
        this.phone = phone;

        // Checking citizen ID length
        if (citizenId.length() == 16) {
            this.citizenId = citizenId;
        } else {
            System.out.println("Kindly provide a valid 16-character Citizen ID");
            this.citizenId = citizenId;
        }
    }

    public String getCitizenId() {
        return citizenId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
