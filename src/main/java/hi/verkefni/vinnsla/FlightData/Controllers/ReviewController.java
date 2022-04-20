package hi.verkefni.vinnsla.FlightData.Controllers;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

public class ReviewController {
    private ArrayList<Review> allReviews;
    FlightDB fdb = new FlightDB();
    private Connection connection = null;
    private CustomerController cc;;
    private FlightController fc;

    public void main(String[] args) {
        UpdateRating(fc.GetFlightByFlightNumber("123AB"), 5.0);
    }
    public ReviewController()  {
        cc = new CustomerController();
        fc = new FlightController();
        allReviews = GetAllReviews();
    }

    public ArrayList<Review> GetAllReviews() {
        fdb.ConnectDriver();
        String query = "SELECT * FROM Reviews";
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("Id");
                long ssno = rs.getLong("CustomerSsno");
                String name = rs.getString("CustomerName");
                String customerEmail = rs.getString("CustomerEmail");

                LocalDate date = LocalDate.parse(rs.getString("date"));
                Customer customer = cc.GetCustomerByEmail(customerEmail);

                double rating = rs.getDouble("rating");
                String text = rs.getString("text");
                String flightNumber = rs.getString("FlightNo");

                Flight f = fc.GetFlightByFlightNumber(flightNumber);

                Review review = new Review(f, date, rating, text, customer);
                reviews.add(review);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }

        return reviews;
    }

    public void CreateNewReview(Review review) {
        fdb.ConnectDriver();
        String customerName = review.getCustomer().getName();
        double rating = review.getRating();
        String flightNumber = review.getFlight().getFlightNumber();
        String text = review.getText();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            String newReview = "INSERT INTO Reviews(customerName, rating, text, flightNo) VALUES(?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(newReview);
            ps.setString(1, customerName);
            ps.setDouble(2, rating);
            ps.setString(3, text);
            ps.setString(4, flightNumber);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
    }

    public Review CreateNewReview(Flight flight, LocalDate date, double rating, String text, Customer customer) {
        Review review = new Review(flight, date, rating, text, customer);
        allReviews.add(review);
        CreateNewReview(review);
        UpdateRating(flight, rating);
        return review;
    }

    private void UpdateRating(Flight flight, double rating) {
        if (rating > 5.0 || rating < 0.0) {
            System.out.println("Rating must be on the form 0 <= rating <= 5.0");
        }

        fdb.ConnectDriver();

        int numberOfRatings;
        double totalRating;
        String flightNo = flight.getFlightNumber();
        String queryNumberOfRatings = "SELECT NumberOfRatings FROM Flights WHERE FlightNo='" + flightNo + "'";
        String queryTotalRating = "SELECT TotalRating FROM Flights WHERE FlightNo='" + flightNo + "'";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryNumberOfRatings);
            rs.next();
            numberOfRatings = rs.getInt("NumberOfRatings");
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryTotalRating);
            rs.next();
            totalRating = rs.getDouble("TotalRating");
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        totalRating += rating;
        numberOfRatings++;
        double newRating = totalRating / (double) numberOfRatings;
        String query = "UPDATE Flights SET TotalRating=" + totalRating + ", NumberOfRatings=" + numberOfRatings + ", AverageRating=" + newRating + " WHERE FlightNo='" + flightNo + "'";
        fdb.UpdateDatabase(query);
    }

    public void DeleteReview(Review review) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Reviews WHERE FlightNo='" + review.getFlight().getFlightNumber() + "' AND CustomerEmail=" + review.getCustomer().getEmail();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            fdb.CloseConnection(connection);
        }
        allReviews = GetAllReviews();
    }

    public ArrayList<Review> GetAllReviewsByEmail(String customerEmail) {
        fdb.ConnectDriver();
        ArrayList<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE CustomerEmail='" + customerEmail + "'";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String flightNumber = rs.getString("FlightNo");
                LocalDate date = LocalDate.parse(rs.getString("date"));
                double rating = rs.getDouble("rating");
                String text = rs.getString("text");
                Flight f = fc.GetFlightByFlightNumber(flightNumber);

                Review review = new Review(f, date, rating, text, cc.GetCustomerByEmail(customerEmail));
                reviews.add(review);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
        return reviews;
    }

    // Edits a review
    public void edit(Review rev, Flight flight, LocalDate date, Double rating, String text) {
        rev.setFlight(flight);
        rev.setDate(date);
        rev.setRating(rating);
        rev.setText(text);
    }


}
