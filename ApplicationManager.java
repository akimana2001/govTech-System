import java.util.*;
import java.io.*;

public class ApplicationManager {

    private ArrayList<ServiceApplication> applications;

    private final String DATA_FILE = "applications.txt";
    private final String REPORT_FILE = "revenue_report.txt";

    public ApplicationManager() {
        applications = new ArrayList<>();
        loadFromFile();
    }

    public void addApplication(ServiceApplication app) {
        applications.add(app);
        saveToFile();
    }

    public ServiceApplication findApplicationById(String id)
            throws ApplicationNotFoundException {

        for (ServiceApplication app : applications) {
            if (app.getApplicationId().equals(id)) {
                return app;
            }
        }

        throw new ApplicationNotFoundException(
                "Application with ID " + id + " not found."
        );
    }
    public void approveApplication(String id)
            throws ApplicationNotFoundException,
            InvalidStatusTransitionException {

        ServiceApplication app = findApplicationById(id);

        if (app.getStatus() == ApplicationStatus.APPROVED) {
            throw new InvalidStatusTransitionException(
                    "Application already approved."
            );
        }

        app.setStatus(ApplicationStatus.APPROVED);
        saveToFile();
        System.out.println("Application approved successfully.");
    }
    public void rejectApplication(String id)
            throws ApplicationNotFoundException,
            InvalidStatusTransitionException {

        ServiceApplication app = findApplicationById(id);

        if (app.getStatus() == ApplicationStatus.REJECTED) {
            throw new InvalidStatusTransitionException(
                    "Application already rejected."
            );
        }

        app.setStatus(ApplicationStatus.REJECTED);
        saveToFile();
        System.out.println("Application rejected successfully.");
    }
    public void displayAllApplications() {

        if (applications.isEmpty()) {
            System.out.println("No applications available.");
            return;
        }

        for (ServiceApplication app : applications) {
            app.display();
        }
    }
    public void saveToFile() {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(DATA_FILE))) {

            for (ServiceApplication app : applications) {
                writer.println(app.toFileString());
            }

        } catch (IOException e) {
            System.out.println("Error saving applications.");
        }
    }

    public void loadFromFile() {

        File file = new File(DATA_FILE);

        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ServiceApplication app =
                        ServiceApplication.fromFileString(line);

                if (app != null) {
                    applications.add(app);
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading applications.");
        }
    }

    public void generateRevenueReport() {

        double totalRevenue = 0;

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(REPORT_FILE))) {

            writer.println("REVENUE REPORT");
            writer.println("---------------------");

            for (ServiceApplication app : applications) {
                if (app.getStatus() == ApplicationStatus.APPROVED) {
                    totalRevenue += app.getServiceFee();
                }
            }

            writer.println("Total Revenue: " + totalRevenue);

        } catch (IOException e) {
            System.out.println("Error generating report.");
        }
    }
}