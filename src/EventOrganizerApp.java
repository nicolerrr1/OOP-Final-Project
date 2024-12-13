package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EventOrganizerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();
        int choice;

        do {
            System.out.println("WELCOME TO EVENT HUB!!");
            System.out.println("1. Create Event");
            System.out.println("2. View All Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer for your choice.");
                scanner.nextLine(); 
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    
                    int id = 0;
                    while (true) {
                        System.out.print("Enter event ID: ");
                        if (scanner.hasNextInt()) {
                            id = scanner.nextInt();
                            scanner.nextLine(); 
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer for event ID.");
                            scanner.nextLine(); 
                        }
                    }

                    System.out.print("Enter event name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter event description: ");
                    String description = scanner.nextLine();

                    LocalDate desiredDate = null;
                    while (desiredDate == null) {
                        System.out.print("Enter event date (YYYY-MM-DD): ");
                        String dateInput = scanner.nextLine();
                        try {
                            desiredDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        }
                    }

                    if (!DatabaseConnection.isDateAvailable(desiredDate)) {
                        LocalDate suggestedDate = DatabaseConnection.suggestNextAvailableDate(desiredDate);
                        System.out.println("The date " + desiredDate + " is already booked.");
                        System.out.println("Suggested next available date: " + suggestedDate);
                        System.out.print("Do you want to proceed with the suggested date? (yes/no): ");
                        String proceed = scanner.nextLine();
                        if (proceed.equalsIgnoreCase("yes")) {
                            desiredDate = suggestedDate;
                        } else {
                            System.out.println("Event creation cancelled.");
                            break;
                        }
                    }

                    Event newEvent = new Event(id, name, description, desiredDate) {
                        @Override
                        public void displayDetails() {
                            System.out.println("ID: " + getId() + ", Name: " + getName() + ", Description: " + getDescription() + ", Date: " + getDate());
                        }
                    };
                    eventManager.manageEvent(1, newEvent, 0, null, null, null);
                    break;

                case 2:
                    eventManager.manageEvent(2, null, 0, null, null, null);
                    break;

                case 3:
                    int updateId = 0;
                    while (true) {
                        System.out.print("Enter event ID to update: ");
                        if (scanner.hasNextInt()) {
                            updateId = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer for event ID.");
                            scanner.nextLine(); 
                        }
                    }

                    System.out.print("Enter new event name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new event description: ");
                    String newDescription = scanner.nextLine();

                    LocalDate newDate = null;
                    while (newDate == null) {
                        System.out.print("Enter new event date (YYYY-MM-DD): ");
                        String newDateInput = scanner.nextLine();
                        try {
                            newDate = LocalDate.parse(newDateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        }
                    }

                    eventManager.manageEvent(3, null, updateId, newName, newDescription, newDate);
                    break;

                case 4:

                    int deleteId = 0;
                    while (true) {
                        System.out.print("Enter event ID to delete: ");
                        if (scanner.hasNextInt()) {
                            deleteId = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer for event ID.");
                            scanner.nextLine();
                        }
                    }

                    eventManager.manageEvent(4, null, deleteId, null, null, null);
                    break;

                case 5:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
