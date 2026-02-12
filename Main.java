
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();

        while (true) {

            System.out.println("---------------------------------");
            System.out.println(" DIGITAL GOVERNMENT SERVICE SYSTEM ");
            System.out.println("---------------------------------");
            System.out.println("1. Add New Application");
            System.out.println("2. Approve Application");
            System.out.println("3. Reject Application");
            System.out.println("4. Search Application By ID");
            System.out.println("5. View All Applications");
            System.out.println("6. Generate Revenue Report");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 7) {
                manager.saveToFile();
                System.out.println("Goodbye!");
                break;
            }

            switch (choice) {
                case 1:

                    System.out.print("Enter Citizen ID: ");
                    String citizenId = input.nextLine();

                    System.out.print("Enter Citizen Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = input.nextLine();

                    Citizen citizen = new Citizen(citizenId, name, phone);

                    System.out.println("Choose Service:");
                    System.out.println("1. Birth Certificate");
                    System.out.println("2. Driving Test");
                    System.out.print("Choice: ");

                    int serviceChoice = input.nextInt();
                    input.nextLine();

                    GovernmentService service = null;

                    if (serviceChoice == 1) {
                        service = new BirthCertification();
                    }
                    else if (serviceChoice == 2) {
                        service = new DrivingTestService();
                    }
                    else {
                        System.out.println("Invalid service.");
                        break;
                    }

                    System.out.print("Enter Application ID: ");
                    String appId = input.nextLine();

                    ServiceApplication app =
                            new ServiceApplication(
                                    appId,
                                    citizen.getName(),
                                    service.getServiceName(),
                                    service.getServiceFee()
                            );

                    manager.addApplication(app);

                    System.out.println("Application added successfully!");
                    break;

                case 2:
                    try {
                        System.out.print("Enter Application ID: ");
                        manager.approveApplication(input.nextLine());
                    }
                    catch (ApplicationNotFoundException |
                           InvalidStatusTransitionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                case 3:
                    try {
                        System.out.print("Enter Application ID: ");
                        manager.rejectApplication(input.nextLine());
                    }
                    catch (ApplicationNotFoundException |
                           InvalidStatusTransitionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Application ID: ");
                        ServiceApplication found =
                                manager.findApplicationById(input.nextLine());
                        found.display();
                    }
                    catch (ApplicationNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                case 5:
                    manager.displayAllApplications();
                    break;
                case 6:
                    manager.generateRevenueReport();
                    System.out.println("Revenue report generated.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        input.close();
    }
}