package hi.verkefni.vinnsla.FlightData.Controllers;


import java.sql.*;
import java.util.ArrayList;
import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;


public class BookingController {
    private static Connection connection = null;
    CustomerController cc;
    FlightController fc = new FlightController();
    private ArrayList<Booking> allBookings;
    private FlightDB fdb;

    public BookingController() {
        fdb = new FlightDB();
        allBookings = GetAllBookings();
    }

    public ArrayList<Booking> GetAllBookings() {
        fdb.ConnectDriver();
        String query = "SELECT * FROM Bookings";
        ArrayList<Booking> bookings = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {

                int bookingId = rs.getInt("BookingId");
                long customerSsno = rs.getLong("CustomerSsno");
                boolean pillowOrdered = rs.getBoolean("PillowOrdered");
                String flightNo = rs.getString("FlightNo");
                Flight flight = fc.GetFlightByFlightNumber(flightNo);
                Customer customer = cc.GetCustomerBySsno(customerSsno);
                Seat s = new Seat(1, 'a');

                Booking booking = new Booking(s, flight, customer, pillowOrdered, bookingId, null);
                bookings.add(booking);
            }
            rs.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return bookings;
    }

    private void CreateNewBooking(Booking booking) {
        fdb.ConnectDriver();
        System.out.println("Hér");

        int bookingId = booking.getId();
        int seatNum = booking.getSeat().getSeatChar();
        int seatRow = booking.getSeat().getRow();
        String seat = String.format("%d%s", seatNum, seatRow);
        boolean pillowOrdered = booking.getPillow();
        String customerEmail = booking.getCustomer().getEmail();
        long customerSsno = booking.getCustomer().getSsno();
        String flightNo = booking.getFlight().getFlightNumber();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            String newBooking = "INSERT INTO Bookings(bookingId, seat, pillowOrdered, customerEmail, CustomerSsno, FlightNo, CustomerName) VALUES(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(newBooking);
            ps.setInt(1, bookingId);
            ps.setString(2, seat);
            ps.setBoolean(3, pillowOrdered);
            ps.setString(4, customerEmail);
            ps.setLong(5, customerSsno);
            ps.setString(6, flightNo);
            ps.setString(7, booking.getCustomer().getName());

            ps.executeUpdate();
            DecrementSeats(fc.GetFlightByFlightNumber(flightNo));
            System.out.println("Successfully booked!");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
    }

    public void CreateNewBooking(Seat seat, Flight flight, Customer customer, boolean pillow, int id, Review review) {
        if (flight.getSeatsLeft() > 0) {
            System.out.println(flight.getSeatsLeft());
            Booking booking = new Booking(seat, flight, customer, pillow, id, review);
            CreateNewBooking(booking);
            System.out.println("Creating booking");
        } else System.out.println("Flight is full, cannot book this flight");
    }

    private void DeleteBookingByCustomerAndFlightNr(Customer customer, String flightNr) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Bookings WHERE FlightNo='" + flightNr + "' AND CustomerSsno=" + customer.getSsno();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            fdb.CloseConnection(connection);
        }
        allBookings = GetAllBookings();
    }

    public void DeleteBooking(Booking booking) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Bookings WHERE CustomerSsno='" + booking.getCustomer().getSsno() + "' AND FlightNo='" + booking.getFlight() + "'";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            fdb.CloseConnection(connection);
        }
        IncrementSeats(booking.getFlight());
        allBookings = GetAllBookings();
    }

    // Hjálparfall
    private void DecrementSeats(Flight flight) {
        int seatsLeft = flight.getSeatsLeft() - 1;
        flight.setSeatsLeft(seatsLeft);
        String query = "UPDATE Flights SET SeatsLeft=" + seatsLeft + " WHERE FlightNo ='" + flight.getFlightNumber() + "'";
        fdb.UpdateDatabase(query);
    }

    // Hjálparfall
    private void IncrementSeats(Flight flight) {
        int seatsLeft = flight.getSeatsLeft() + 1;
        flight.setSeatsLeft(seatsLeft);
        String query = "UPDATE Flights SET SeatsLeft=" + seatsLeft + " WHERE FlightNo ='" + flight.getFlightNumber() + "'";
        fdb.UpdateDatabase(query);
    }

    public static void main(String[] args) {

    }



}





