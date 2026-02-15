package Services;

import models.ApplicationStatus;
import models.Citizen;

public class ServiceApplication {

    private String applicationId;
    private Citizen citizen;
    private GovernmentService service;
    private ApplicationStatus status;

    public ServiceApplication(String applicationId, Citizen citizen, GovernmentService service) {
        this.applicationId = applicationId;
        this.citizen = citizen;
        this.service = service;
        this.status = ApplicationStatus.PENDING;
    }

    public String getApplicationId() { return applicationId; }
    public Citizen getCitizen() { return citizen; }
    public GovernmentService getService() { return service; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public double getServiceFee() { return service.calculateFee(); }

    public void display() {
        System.out.println("Application ID: " + applicationId);
        System.out.println("Citizen ID: " + citizen.getCitizenId());
        System.out.println("Citizen Name: " + citizen.getName());
        System.out.println("Service: " + service.getServiceName());
        System.out.println("Fee: " + service.calculateFee());
        System.out.println("Status: " + status);
        System.out.println("----------------------------");
    }

    public String toFileString() {
        return applicationId + "," +
                citizen.getCitizenId() + "," +
                citizen.getName() + "," +
                citizen.getPhone() + "," +
                service.getServiceName() + "," +
                service.calculateFee() + "," +
                status;
    }

    public static ServiceApplication fromFileString(String line) {
        String[] parts = line.split(",");
        Citizen citizen = new Citizen(parts[1], parts[2], parts[3]);
        GovernmentService service;

        if (parts[4].equals("Birth Certificate")) service = new BirthCertification();
        else service = new DrivingTestService();

        ServiceApplication app = new ServiceApplication(parts[0], citizen, service);
        app.setStatus(ApplicationStatus.valueOf(parts[6]));
        return app;
    }
}
