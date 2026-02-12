public class ServiceApplication {

    private String applicationId;
    private String citizenName;
    private String serviceName;
    private double serviceFee;
    private ApplicationStatus status;

    public ServiceApplication(String applicationId, String citizenName,
                              String serviceName, double serviceFee) {

        this.applicationId = applicationId;
        this.citizenName = citizenName;
        this.serviceName = serviceName;
        this.serviceFee = serviceFee;
        this.status = ApplicationStatus.PENDING;
    }


    public String getApplicationId() {
        return applicationId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }


    public void display() {
        System.out.println("Application ID: " + applicationId);
        System.out.println("Citizen Name: " + citizenName);
        System.out.println("Service: " + serviceName);
        System.out.println("Fee: " + serviceFee);
        System.out.println("Status: " + status);
        System.out.println("----------------------------");
    }



    public String toFileString() {
        return applicationId + "," +
                citizenName + "," +
                serviceName + "," +
                serviceFee + "," +
                status;
    }


    public static ServiceApplication fromFileString(String line) {

        String[] parts = line.split(",");

        ServiceApplication app = new ServiceApplication(
                parts[0],
                parts[1],
                parts[2],
                Double.parseDouble(parts[3])
        );

        app.setStatus(ApplicationStatus.valueOf(parts[4]));

        return app;
    }
}
