package hi.verkefni.vinnsla.FlightData.Controllers;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

import java.sql.*;
import java.util.ArrayList;

public class FlightController {
    static FlightDB fdb = new FlightDB();
    static Connection connection = null;
    public ArrayList<Flight> GetAllFlights() {

        return null;
    }

    public void CreateNewFlight(String flightNumber, String arrival,
                                String departure, java.sql.Date date, double cost, int seatsLeft, String aircraft)
    {
        fdb.ConnectDriver();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            String newFlight = "INSERT INTO Flights(flightNumber, arrival, departure, date, cost, seatsLeft, aircraft) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(newFlight);
            ps.setString(1, flightNumber);
            ps.setString(2, arrival);
            ps.setString(3, departure);
            ps.setDate(4, date);
            ps.setDouble(5, cost);
            ps.setInt(6, seatsLeft);
            ps.setString(7, aircraft);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
    }

    public static Flight GetFlightByFlightNumber(String flightNumber) {
        fdb.ConnectDriver();
        Flight flight;
        String query = "SELECT * FROM Flights WHERE FlightNo='" + flightNumber + "'";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            rs.next();
            String airplaneName = rs.getString("AirplaneName");
            String departure = rs.getString("Departure");
            String arrival = rs.getString("Arrival");
            boolean hasEntertainment = rs.getBoolean("hasEntertainment");
            int seatsLeft = rs.getInt("SeatsLeft");

            String date = rs.getString("date");
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5,7));
            int day = Integer.parseInt(date.substring(8, 10));
            java.util.Date d = new java.util.Date(year, month, day);

            flight = new Flight(departure, arrival, d, flightNumber, 10000, seatsLeft);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            flight = null;
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return flight;
    }

    public ArrayList<Flight> search(String query) {
        fdb.ConnectDriver();
        ArrayList<Flight> flights = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String arrival = rs.getString("Arrival");
                String flightNumber = rs.getString("FlightNo");
                String departure = rs.getString("Departure");
                String date = rs.getString("date");
                int seatsLeft = rs.getInt("SeatsLeft");
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5,7));
                int day = Integer.parseInt(date.substring(8, 10));
                java.util.Date d = new java.util.Date(year, month, day);

                Flight flight = new Flight(departure, arrival, d, flightNumber, 25, seatsLeft);
                flights.add(flight);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return flights;
    }
}
