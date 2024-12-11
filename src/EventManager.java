package src;

import java.time.LocalDate;


public class EventManager {
    private EventDAO eventDAO = new EventDAO();

    public void manageEvent(int choice, Event event, int id, String name, String description, LocalDate date) {
        switch (choice) {
            case 1: // Create
                eventDAO.createEvent(event);
                System.out.println("Event created successfully.");
                break;
            case 2: // Read
                eventDAO.readEvents();
                break;
            case 3: // Update
                eventDAO.updateEvent(id, name, description, date);
                break;
            case 4: // Delete
                eventDAO.deleteEvent(id);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
