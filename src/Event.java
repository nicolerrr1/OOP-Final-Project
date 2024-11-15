package src;
import java.util.Date;

public class Event {
    private int eventId;
    private String name;
    private Date date;
    private String location;
    private String description;

    public Event(int eventId, String name, Date date, String location, String description) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
    }

    public Event(String name, Date date, String location, String description) {
        this(0, name, date, location, description);
    }

    public int getEventId() { return eventId; }
    public String getName() { return name; }
    public Date getDate() { return date; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Event ID: " + eventId + ", Name: " + name + ", Date: " + date + ", Location: " + location + ", Description: " + description;
    }
}
