package src;

public class EventDAO {
    // Check if a date is already occupied by another event
    public boolean isDateOccupied(Date date) {
        String query = "SELECT * FROM Events WHERE date = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // If there is a result, the date is occupied

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all occupied dates in a specific month and year
    public List<Date> getOccupiedDates(int month, int year) {
        List<Date> occupiedDates = new ArrayList<>();
        String query = "SELECT date FROM Events WHERE MONTH(date) = ? AND YEAR(date) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                occupiedDates.add(rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return occupiedDates;
    }

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
        return events;
    }

    public void deleteEvent(int id) {
        DatabaseConnection.deleteEvent(id);
        System.out.println("Event deleted successfully.");
    }
}
