package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if (isDateOccupied(event.getDate())) {
            System.out.println("The selected date is already occupied by another event. Please choose another date.");
            return;
        }

        String query = "INSERT INTO Events (name, date, location, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, event.getName());
            stmt.setDate(2, new java.sql.Date(event.getDate().getTime()));
            stmt.setString(3, event.getLocation());
            stmt.setString(4, event.getDescription());

            stmt.executeUpdate();
            System.out.println("Event created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Events";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Event event = new Event(
                    rs.getInt("eventId"),
                    rs.getString("name"),
                    rs.getDate("date"),
                    rs.getString("location"),
                    rs.getString("description")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void deleteEvent(int eventId) {
        String query = "DELETE FROM Events WHERE eventId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, eventId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event deleted successfully.");
            } else {
                System.out.println("Event ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
