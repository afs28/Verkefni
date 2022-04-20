package hi.verkefni.vinnsla.FlightData.Controllers;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

public class CustomerController {
    private static final String DB_PATH = "Verkefni/src/main/java/hi/verkefni/vinnsla/FlightData/Database" + File.separator + "flightDB.db";
    private Connection connection = null;
    private ArrayList<Customer> allCustomers;
    private FlightDB fdb;

    public CustomerController() {
        fdb = new FlightDB();
        allCustomers = GetAllCustomers();
    }

    public ArrayList<Customer> GetAllCustomers() {
        fdb.ConnectDriver();
        String query = "SELECT * FROM Customers";
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String email = rs.getString("CustomerEmail");
                int phone = rs.getInt("CustomerPhone");
                String name = rs.getString("CustomerName");
                String address = rs.getString("CustomerAddress");
                int postalCode = rs.getInt("CustomerPostalCode");
                String nationality = rs.getString("CustomerNationality");

                Customer customer = new Customer(name, phone, email, address, postalCode, nationality);
                customers.add(customer);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return customers;
    }

    public void CreateNewCustomer(Customer customer) {
        fdb.ConnectDriver();
        String name = customer.getName();
        int phone = customer.getPhone();
        String email = customer.getEmail();
        String address = customer.getAddress();
        int postalCode = customer.getPostalCode();
        String nation = customer.getNation();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            String newCustomer = "INSERT INTO Customers(CustomerEmail, CustomerPhone, CustomerName, CustomerAddress, CustomerPostalCode, CustomerNationality) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(newCustomer);
            ps.setString(1, email);
            ps.setInt(2, phone);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setInt(5, postalCode);
            ps.setString(6, nation);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
        allCustomers = GetAllCustomers();
    }

    public void CreateNewCustomer(String name, long ssno, int phone, String email, String address, int postalCode, String nation) {
        Customer customer = new Customer(name, phone, email, address, postalCode, nation);
        CreateNewCustomer(customer);
    }

    public void DeleteCustomer(long customerSsno) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Customers WHERE CustomerSsno=" + customerSsno;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            fdb.CloseConnection(connection);
        }
        allCustomers = GetAllCustomers();
    }

    // TODO
    public Customer GetCustomerByEmail(String customerEmail) {
        fdb.ConnectDriver();
        Customer customer;
        String query = "SELECT * FROM Customers WHERE CustomerEmail='" + customerEmail + "'";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            rs.next();
            long ssno = rs.getLong("CustomerSsno");
            int phone = rs.getInt("CustomerPhone");
            String name = rs.getString("CustomerName");
            String address = rs.getString("CustomerAddress");
            int postalCode = rs.getInt("CustomerPostalCode");
            String nationality = rs.getString("CustomerNationality");

            customer = new Customer(name, phone, customerEmail, address, postalCode, nationality);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            customer = null;
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return customer;
    }
}
