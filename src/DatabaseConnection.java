package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static List<Event> events = new ArrayList<>();

    public static void addEvent(Event event) {
        events.add(event);
    }

    public static List<Event> getAllEvents() {
        return events;
    }

    public static void deleteEvent(int id) {
        events.removeIf(event -> event.getId() == id);
    }

    public static Event getEventById(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public static boolean isDateAvailable(LocalDate date) {
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                return false;
            }
        }
        return true;
    }

    public static LocalDate suggestNextAvailableDate(LocalDate desiredDate) {
        LocalDate suggestedDate = desiredDate;
        while (!isDateAvailable(suggestedDate)) {
            suggestedDate = suggestedDate.plusDays(1);
        }
        return suggestedDate;
    }
}
