package hi.verkefni.vinnsla.FlightData.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import hi.verkefni.vinnsla.FlightData.Objects.*;

public class FlightDB {
    private static Connection connection = null;

    public FlightDB() {
    }

    public String BookSeat(Seat seat, String flightNumber) {
        ConnectDriver();
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
            CloseConnection(connection);
        }
    }

    public void CancelSeat(Seat seat) {
        ConnectDriver();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            // TODO eftir að klára hér að finna sætanúmer og flugnúmer
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
            CloseConnection(connection);
        }
    }

    public static void main(String[] args) {
    }

    public static void ConnectDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void CloseConnection(Connection connection) {
        try {
            if (connection != null) connection.close();
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void UpdateDatabase(String query) {
        ConnectDriver();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            CloseConnection(connection);
        }
    }

}
