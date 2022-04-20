package hi.verkefni.vinnsla.FlightData.Controllers;


import java.sql.*;
import java.util.ArrayList;
import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

public class BookingController {
    private Connection connection = null;
    static CustomerController cc;
    static FlightController fc = new FlightController();
    private ArrayList<Booking> allBookings;
    private FlightDB fdb;

    public BookingController() {
        fdb = new FlightDB();
        allBookings = GetAllBookings();
    }
    public String BookSeat(Seat seat, String flightNumber) {
        fdb.ConnectDriver();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            int row = seat.getRow();
            int seatChar = seat.seatCharToInt();
            String query = "SELECT isBooked FROM Seats WHERE SeatNr = " + row + "" + seatChar + " AND FlightNumber = " + flightNumber;
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            try {
                rs.next();
                if (!rs.getBoolean(1)) {
                    String bookSeatQuery = "UPDATE TABLE Seats SET isBooked = True WHERE SeatNr = '" + row + "" + seatChar + "' AND FlightNumber = '" + flightNumber + "'";
                    System.out.println(bookSeatQuery);
                    Statement s = connection.createStatement();
                    s.executeUpdate(bookSeatQuery);
                    return "Seat Booked!";
                }
                else return "Cannot book seat, it is already taken!";
            }
            finally {
                rs.close();
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return "Could not book seat";
        }
        finally {
            fdb.CloseConnection(connection);
        }
    }
    public void CancelSeat(Seat seat) {
        fdb.ConnectDriver();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            int row = seat.getRow();
            int seatChar = seat.seatCharToInt();

            String query = "UPDATE Seats SET isBooked=false WHERE SeatNr = ? AND FlightNo = ?";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
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
                String customerEmail = rs.getString("CustomerEmail");
                boolean pillowOrdered = rs.getBoolean("PillowOrdered");
                int numberOfPassengers = rs.getInt("NumberOfPassengers");
                String flightNo = rs.getString("FlightNo");
                Flight flight = fc.GetFlightByFlightNumber(flightNo);
                Customer customer = cc.GetCustomerByEmail(customerEmail);
                Seat s = new Seat(1, 'a');

                Booking booking = new Booking(s, flight, customer, pillowOrdered, null, numberOfPassengers);
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
    public Booking GetBookingByCustomerAndFlightNumber(Customer customer, String flightNumber) {
        fdb.ConnectDriver();
        String query = "SELECT * FROM Bookings WHERE CustomerEmail='" + customer.getEmail() + "' AND FlightNo='" + flightNumber + "'";
        ResultSet rs;
        Booking booking = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            rs.next();
            String customerEmail = rs.getString("CustomerEmail");
            boolean pillowOrdered = rs.getBoolean("PillowOrdered");
            int numberOfPassengers = rs.getInt("NumberOfPassengers");
            String flightNo = rs.getString("FlightNo");
            Flight flight = fc.GetFlightByFlightNumber(flightNo);
            Seat s = new Seat(1, 'a');

            booking = new Booking(s, flight, customer, pillowOrdered, null, numberOfPassengers);

            rs.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return booking;
    }
    private void CreateNewBooking(Booking booking) {
        fdb.ConnectDriver();
        int seatNum = booking.getSeat().getSeatChar();
        int seatRow = booking.getSeat().getRow();
        String seat = String.format("%d%s", seatNum, seatRow);
        boolean pillowOrdered = booking.getPillow();
        String customerEmail = booking.getCustomer().getEmail();
        String flightNo = booking.getFlight().getFlightNumber();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            String newBooking = "INSERT INTO Bookings(seat, pillowOrdered, customerEmail, FlightNo) VALUES(?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(newBooking);
            ps.setString(1, seat);
            ps.setBoolean(2, pillowOrdered);
            ps.setString(3, customerEmail);
            ps.setString(4, flightNo);

            ps.executeUpdate();
            DecrementSeats(fc.GetFlightByFlightNumber(flightNo), booking.getNumberOfPassengers());
            System.out.println("Successfully booked!");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
    }
    public void CreateNewBooking(Seat seat, Flight flight, Customer customer, boolean pillow, Review review, int numberOfPassengers) {
        if (flight.getSeatsLeft() > numberOfPassengers) {
            Booking booking = new Booking(seat, flight, customer, pillow, review, numberOfPassengers);
            flight.bookSeat(seat);
            CreateNewBooking(booking);
            System.out.println("Creating booking");
        } else System.out.println("Flight is full, cannot book this flight");
    }

    private void DeleteBookingByCustomerAndFlightNr(Customer customer, String flightNr) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Bookings WHERE FlightNo='" + flightNr + "' AND CustomerEmail=" + customer.getEmail();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            DeleteBooking(GetBookingByCustomerAndFlightNumber(customer, flightNr));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            fdb.CloseConnection(connection);
        }
        allBookings = GetAllBookings();
    }
    public void DeleteBooking(Booking booking) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Bookings WHERE CustomerEmail='" + booking.getCustomer().getEmail() + "' AND FlightNo='" + booking.getFlight() + "'";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            fdb.CloseConnection(connection);
        }
        booking.getFlight().freeSeat(booking.getSeat());
        IncrementSeats(booking.getFlight(), booking.getNumberOfPassengers());
        allBookings = GetAllBookings();
    }
    private void DecrementSeats(Flight flight, int numberOfPassengers) {
        int seatsLeft = flight.getSeatsLeft() - numberOfPassengers;
        flight.setSeatsLeft(seatsLeft);
        String query = "UPDATE Flights SET SeatsLeft=" + seatsLeft + " WHERE FlightNo ='" + flight.getFlightNumber() + "'";
        fdb.UpdateDatabase(query);
    }
    private void IncrementSeats(Flight flight, int numberOfPassengers) {
        int seatsLeft = flight.getSeatsLeft() + numberOfPassengers;
        flight.setSeatsLeft(seatsLeft);
        String query = "UPDATE Flights SET SeatsLeft=" + seatsLeft + " WHERE FlightNo ='" + flight.getFlightNumber() + "'";
        fdb.UpdateDatabase(query);
    }
}





