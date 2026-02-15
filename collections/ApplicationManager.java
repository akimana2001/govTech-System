package collections;

import java.io.*;
import java.util.*;

import Services.ServiceApplication;
import models.ApplicationStatus;
import Exceptions.ApplicationNotFoundException;

public class ApplicationManager {

    private List<ServiceApplication> applications = new ArrayList<>();
    private static final String DATA_FILE = "applications.txt";
    private static final String REPORT_FILE = "revenue_report.txt";

    public ApplicationManager() {
        loadFromFile();
    }

    // Adding new application
    public void addApplication(ServiceApplication app) {
        applications.add(app);
        saveToFile();
    }

    // Finding application by ID
    public ServiceApplication findApplicationById(String id) throws ApplicationNotFoundException {
        for (ServiceApplication app : applications) {
            if (app.getApplicationId().equals(id)) return app;
        }
        throw new ApplicationNotFoundException("Application with ID " + id + " not found.");
    }

    // Approving an application
    public void approveApplication(String id) throws ApplicationNotFoundException {
        ServiceApplication app = findApplicationById(id);
        if (app.getStatus() == ApplicationStatus.APPROVED) {
            System.out.println("Application already approved.");
            return;
        }
        app.setStatus(ApplicationStatus.APPROVED);
        saveToFile();
        System.out.println("Application approved successfully.");
    }

    // Rejecting an application
    public void rejectApplication(String id) throws ApplicationNotFoundException {
        ServiceApplication app = findApplicationById(id);
        if (app.getStatus() == ApplicationStatus.REJECTED) {
            System.out.println("Application already rejected.");
            return;
        }
        app.setStatus(ApplicationStatus.REJECTED);
        saveToFile();
        System.out.println("Application rejected successfully.");
    }

    // Displaying all applications
    public void displayAllApplications() {
        if (applications.isEmpty()) {
            System.out.println("No applications available.");
            return;
        }
        for (ServiceApplication app : applications) app.display();
    }

    // Saving applications to file
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (ServiceApplication app : applications) writer.println(app.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving applications.");
        }
    }

    // Loading applications from file
    private void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ServiceApplication app = ServiceApplication.fromFileString(line);
                if (app != null) applications.add(app);
            }
        } catch (Exception e) {
            System.out.println("Error loading applications.");
        }
    }

    // Generating the revenue report
    public void generateRevenueReport() {
        double totalRevenue = 0;
        Map<String, Double> revenueByService = new HashMap<>();

        for (ServiceApplication app : applications) {
            if (app.getStatus() == ApplicationStatus.APPROVED) {
                double fee = app.getServiceFee();
                totalRevenue += fee;

                String serviceName = app.getService().getServiceName();
                revenueByService.put(serviceName, revenueByService.getOrDefault(serviceName, 0.0) + fee);
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(REPORT_FILE))) {
            writer.println("===== REVENUE REPORT =====\n");

            for (String service : revenueByService.keySet()) {
                writer.println(service + " Revenue: " + revenueByService.get(service));
            }

            writer.println("\nTotal Revenue: " + totalRevenue);
        } catch (IOException e) {
            System.out.println("Error generating report.");
        }
    }
}
