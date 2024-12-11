package src;

import java.time.LocalDate;


public class EventDAO {
    public void createEvent(Event event) {
        DatabaseConnection.addEvent(event);
    }

    public void readEvents() {
        for (Event event : DatabaseConnection.getAllEvents()) {
            event.displayDetails();
        }
    }

    public void updateEvent(int id, String newName, String newDescription, LocalDate newDate) {
        Event event = DatabaseConnection.getEventById(id);
        if (event != null) {
            event.setName(newName);
            event.setDescription(newDescription);
            event.setDate(newDate);
            System.out.println("Event updated successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    public void deleteEvent(int id) {
        DatabaseConnection.deleteEvent(id);
        System.out.println("Event deleted successfully.");
    }
}
