package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.DayTripData.Controllers.DayTripController;
import hi.verkefni.vinnsla.DayTripData.Database.BookingDataConnection;
import hi.verkefni.vinnsla.DayTripData.Database.CustomerDataConnection;
import hi.verkefni.vinnsla.DayTripData.Objects.Booking;
import hi.verkefni.vinnsla.DayTripData.Objects.Customer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;


public class Main extends Application {

    private Customer customer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginController login = new LoginController();
        Pair<String, String> user = login.getUser();

        if(user != null){
            CustomerDataConnection customerConnection = new CustomerDataConnection();
            customer = customerConnection.getCustomer(user.getKey(), user.getValue());
            if(customer == null){
                start(primaryStage);
                return;
            }
        }
        else {
            start(primaryStage);
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Best META Search Engine");
        primaryStage.setScene(new Scene(root, 1080, 650));
        primaryStage.show();

        BookingDataConnection bookingDataConn = new BookingDataConnection();
        ObservableList<Booking> bookings = bookingDataConn.getBookings(customer.getCustomerId());
        // for(Booking booking : bookings) customer.addBooking(booking);

        // DayTripController dayTripController = loader.getController();
        // dayTripController.initData(customer);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
