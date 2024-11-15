package src;

import java.util.List;

public class EventManager {
    private EventDAO eventDAO = new EventDAO();

    public void createEvent(Event event) {
        eventDAO.createEvent(event);
    }

    public void listEvents() {
        List<Event> events = eventDAO.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    }

    public boolean isDateOccupied(Date date) {
        return eventDAO.isDateOccupied(date);
    }

    public List<Date> getOccupiedDates(int month, int year) {
        return eventDAO.getOccupiedDates(month, year);
    }
}
