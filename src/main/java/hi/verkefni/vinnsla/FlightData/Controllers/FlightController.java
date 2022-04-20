package hi.verkefni.vinnsla.FlightData.Controllers;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FlightController {
    FlightDB fdb = new FlightDB();
    Connection connection = null;
    private ArrayList<Flight> allFlights;

    public Set<String> GetAllDepartures() {
        fdb.ConnectDriver();
        String query = "SELECT Departure FROM Flights";
        Set<String> departures = new HashSet<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String departure = rs.getString("Departure");
                departures.add(departure);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
        return departures;
    }

    public Set<String> GetAllDestinations() {
        fdb.ConnectDriver();
        String query = "SELECT Arrival FROM Flights";
        Set<String> arrivals = new HashSet<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String arrival = rs.getString("Arrival");
                arrivals.add(arrival);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
        return arrivals;
    }

    public FlightController() {
        allFlights = GetAllFlights();
    }
    public ArrayList<Flight> GetAllFlights() {
        String query = "SELECT * FROM Flights";
        return search(query);
    }

    public void CreateNewFlight(String flightNumber, String arrival,
                                String departure, Date date, double cost, int seatsLeft, String aircraft)
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

    public Flight GetFlightByFlightNumber(String flightNumber) {
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
            boolean hasFood = rs.getBoolean("hasFood");
            double avrRating = rs.getDouble("AverageRating");
            int seatsLeft = rs.getInt("SeatsLeft");
            double cost = rs.getDouble("Cost");
            LocalDate date = LocalDate.parse(rs.getString("date"));

            flight = new Flight(departure, arrival, date, flightNumber, cost, seatsLeft, avrRating, airplaneName, hasFood, hasEntertainment);
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
                String airplaneName = rs.getString("AirplaneName");
                boolean hasFood = rs.getBoolean("hasFood");
                boolean hasEntertainment = rs.getBoolean("hasEntertainment");
                double cost = rs.getDouble("Cost");
                double avrRating = rs.getDouble("AverageRating");
                LocalDate date = LocalDate.parse(rs.getString("date"));
                int seatsLeft = rs.getInt("SeatsLeft");
                Flight flight = new Flight(departure, arrival, date, flightNumber, cost, seatsLeft, avrRating, airplaneName, hasFood, hasEntertainment);
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

    public ArrayList<Flight> GetFlightsByDate(LocalDate date) {
        String query = "SELECT * FROM Flights WHERE date='" + date + "'";
        return search(query);
    }
}
