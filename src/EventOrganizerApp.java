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
            System.out.println("Event Organizer Application");
            System.out.println("1. Create Event");
            System.out.println("2. View All Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter event name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter event description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate desiredDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

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
                    System.out.print("Enter event ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new event name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new event description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new event date (YYYY-MM-DD): ");
                    String newDateInput = scanner.nextLine();
                    LocalDate newDate = LocalDate.parse(newDateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                    eventManager.manageEvent(3, null, updateId, newName, newDescription, newDate);
                    break;

                case 4:
                    System.out.print("Enter event ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
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
