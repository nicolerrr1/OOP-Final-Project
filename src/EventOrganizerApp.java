package src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventOrganizerApp {
    private static EventManager eventManager = new EventManager();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Event Organizer Menu:");
            System.out.println("1. Create Event");
            System.out.println("2. List Events");
            System.out.println("3. Delete Event");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    eventManager.listEvents();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createEvent() {
        try {
            System.out.print("Enter Event Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Date (yyyy-MM-dd): ");
            Date date = dateFormat.parse(scanner.nextLine());
            System.out.print("Enter Location: ");
            String location = scanner.nextLine();
            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            // Get the month and year from the selected date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
            int year = calendar.get(Calendar.YEAR);

            Event event = new Event(name, date, location, description);

            // Check if the date is occupied
            if (eventManager.isDateOccupied(date)) {
                System.out.println("The selected date is already occupied. Suggesting available dates...");

                // Find all occupied dates in the selected month
                List<Date> occupiedDates = eventManager.getOccupiedDates(month, year);

                // Get all dates in the selected month
                List<Date> availableDates = getAvailableDatesInMonth(month, year, occupiedDates);

                System.out.println("Available dates in " + year + "-" + month + ":");
                for (
