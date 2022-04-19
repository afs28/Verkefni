package hi.verkefni.vinnsla.FlightData.Controllers;

import java.sql.*;
import java.util.ArrayList;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

public class CustomerController {
    private static Connection connection = null;
    private ArrayList<Customer> allCustomers;
    private static FlightDB fdb;

    public CustomerController() {
        fdb = new FlightDB();
        allCustomers = GetAllCustomers();
    }

    public static ArrayList<Customer> GetAllCustomers() {
        fdb.ConnectDriver();
        String query = "SELECT * FROM Customers";
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {

                long ssno = rs.getLong("CustomerSsno");
                String email = rs.getString("CustomerEmail");
                int phone = rs.getInt("CustomerPhone");
                String name = rs.getString("CustomerName");
                String address = rs.getString("CustomerAddress");
                int postalCode = rs.getInt("CustomerPostalCode");
                String nationality = rs.getString("CustomerNationality");

                Customer customer = new Customer(name, ssno, phone, email, address, postalCode, nationality);
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
        long ssno = customer.getSsno();
        int phone = customer.getPhone();
        String email = customer.getEmail();
        String address = customer.getAddress();
        int postalCode = customer.getPostalCode();
        String nation = customer.getNation();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            String newCustomer = "INSERT INTO Customers(CustomerSsno, CustomerEmail, CustomerPhone, CustomerName, CustomerAddress, CustomerPostalCode, CustomerNationality) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(newCustomer);
            ps.setLong(1, ssno);
            ps.setString(2, email);
            ps.setInt(3, phone);
            ps.setString(4, name);
            ps.setString(5, address);
            ps.setInt(6, postalCode);
            ps.setString(7, nation);
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
        Customer customer = new Customer(name, ssno, phone, email, address, postalCode, nation);
        CreateNewCustomer(customer);
    }

    public void DeleteCustomer(long customerSsno) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Customers WHERE CustomerSsno=" + customerSsno;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
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
    public static Customer GetCustomerBySsno(long ssno) {
        fdb.ConnectDriver();
        Customer customer;
        String query = "SELECT * FROM Customers WHERE CustomerSsno='" + ssno + "'";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            rs.next();
            String email = rs.getString("CustomerEmail");
            int phone = rs.getInt("CustomerPhone");
            String name = rs.getString("CustomerName");
            String address = rs.getString("CustomerAddress");
            int postalCode = rs.getInt("CustomerPostalCode");
            String nationality = rs.getString("CustomerNationality");

            customer = new Customer(name, ssno, phone, email, address, postalCode, nationality);
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
