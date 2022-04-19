package hi.verkefni.vinnsla.FlightData.Controllers;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

public class ReviewController {
    private ArrayList<Review> allReviews;
    FlightDB fdb = new FlightDB();
    private static Connection connection = null;

    private static CustomerController cc;;
    private static FlightController fc;
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

                String date = rs.getString("date");
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5,7));
                int day = Integer.parseInt(date.substring(8, 10));
                Date d = new Date(year, month, day);

                Customer customer = cc.GetCustomerBySsno(ssno);

                double rating = rs.getDouble("rating");
                String text = rs.getString("text");
                String flightNumber = rs.getString("FlightNo");

                Flight f = fc.GetFlightByFlightNumber(flightNumber);

                Review review = new Review(f, d, rating, text, customer);
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
        long customerSsno = review.getCustomer().getSsno();
        String customerName = review.getCustomer().getName();
        double rating = review.getRating();
        String flightNumber = review.getFlight().getFlightNumber();
        String text = review.getText();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            String newReview = "INSERT INTO Reviews(customerSsno, customerName, rating, text, flightNo) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(newReview);
            ps.setLong(1, customerSsno);
            ps.setString(2, customerName);
            ps.setDouble(3, rating);
            ps.setString(4, text);
            ps.setString(5, flightNumber);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }
    }

    public Review CreateNewReview(Flight flight, Date date, double rating, String text, Customer customer) {
        Review review = new Review(flight, date, rating, text, customer);
        allReviews.add(review);
        CreateNewReview(review);
        return review;
    }

    public ArrayList<Review> GetAllReviewsByName(String customerName) {
        fdb.ConnectDriver();
        String query = "SELECT * FROM Reviews WHERE CustomerName='" + customerName + "'";
        ArrayList<Review> reviews = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("Id");
                long ssno = rs.getLong("CustomerSsno");
                String name = rs.getString("CustomerName");

                String date = rs.getString("date");
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5,7));
                int day = Integer.parseInt(date.substring(8, 10));
                Date d = new Date(year, month, day);

                Customer customer = cc.GetCustomerBySsno(ssno);

                double rating = rs.getDouble("rating");
                String text = rs.getString("text");
                String flightNumber = rs.getString("FlightNo");

                Flight f = fc.GetFlightByFlightNumber(flightNumber);

                Review review = new Review(f, d, rating, text, customer);
                reviews.add(review);
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            fdb.CloseConnection(connection);
        }

        if (reviews.size() == 0) return null;
        return reviews;
    }
    public void DeleteReview(Review review) {
        fdb.ConnectDriver();
        String query = "DELETE FROM Reviews WHERE FlightNo='" + review.getFlight().getFlightNumber() + "' AND CustomerSsno=" + review.getCustomer().getSsno();
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


    // TODO
    public ArrayList<Review> GetAllReviewsBySsno(long ssno) {
        fdb.ConnectDriver();
        ArrayList<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE CustomerSsno=" + ssno + "";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:flightDB.db");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                String flightNumber = rs.getString("FlightNo");
                String date = rs.getString("date");
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5,7));
                int day = Integer.parseInt(date.substring(8, 10));
                Date d = new Date(year, month, day);
                double rating = rs.getDouble("rating");
                String text = rs.getString("text");
                Flight f = fc.GetFlightByFlightNumber(flightNumber);

                Review review = new Review(f, d, rating, text, cc.GetCustomerBySsno(ssno));
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

    /*
    // Constructs a new Review and adds it to the reviews array and returns the review;
    public Review create(Flight flight, Date date, double rating, String text) throws ClassNotFoundException {
        Review review = new Review(flight, date, rating, text);
        Review[] newRevs = new Review[reviews.length + 1];
        int n = newRevs.length;
        for(int i = 0; i < n; i++){
            if(i == n - 1){
                newRevs[i] = review;
            }
            newRevs[i] = reviews[i];
        }
        reviews = newRevs;
        // það þarf síðan að finna leið til að bæta við review-inu í gagnasafnið(update)!!!
        return review;
    }


    // Deletes an already exsisting review
    public void delete(Review rev) {
        int n = reviews.length;
        Review[] newRevs = new Review[reviews.length - 1];
        int j = 0;
        for(int i = 0; i < n; i++){
            if(rev != reviews[i]){
                newRevs[j] = reviews[i];
                j++;
            }
        }
        // það þarf síðan að finna leið til að eyða review-inu úr gagnasafninu(update)!!!
        rev = null;
    }
     */

    // Edits a review
    public void edit(Review rev, Flight flight, Date date, Double rating, String text) {
        rev.setFlight(flight);
        rev.setDate(date);
        rev.setRating(rating);
        rev.setText(text);
    }


}
