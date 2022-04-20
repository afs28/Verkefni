package hi.verkefni.vinnsla.FlightData.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import hi.verkefni.vinnsla.FlightData.Objects.*;

public class FlightDB {
    private Connection connection = null;

    public FlightDB() {
    }

    public void ConnectDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void CloseConnection(Connection connection) {
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
