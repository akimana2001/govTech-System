public class Citizen {
    private String citizenId;
    private String name;
    private String phone;

    public Citizen(String citizenId,String name, String phone){
        this.citizenId = citizenId;
        this.name = name;
        this.phone = phone;
    }
    public String getCitizenId(){
        return citizenId;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
}