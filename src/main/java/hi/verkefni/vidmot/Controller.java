package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //FXML breytur úr SceneBuilder
    @FXML
    public ChoiceBox fxOneWayRoundTrip;
    @FXML
    public ChoiceBox fxHotelsNoRooms;
    @FXML
    public ChoiceBox fxHotelsNoAdults;
    @FXML
    public ChoiceBox<String> fxHotelsNoChildren;
    @FXML
    public ComboBox fxDayTripsDuration;
    @FXML
    public ComboBox fxDayTripsArrivalTime;
    @FXML
    private ChoiceBox<String> fxFlightsNoPassengers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setti inn virkni fyrir choicebox í Flights tab
        setFXFlightsChoiceBox();

        //virkni fyrir choice box í Hotels tab
        setFXHotelChoiceBox();

        //virkni fyrir combo box í DayTrips tab
       // setFXDayTripsChoiceBox();
    }

    //Kannski betra að geyma þetta í method()
    public void setFXHotelChoiceBox() {
        //virkni fyrir choice box í Hotels tab
        fxHotelsNoRooms.getItems().removeAll(fxHotelsNoRooms.getItems());
        fxHotelsNoRooms.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        fxHotelsNoRooms.getSelectionModel().select("1");

        fxHotelsNoAdults.getItems().removeAll(fxHotelsNoAdults.getItems());
        fxHotelsNoAdults.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        fxHotelsNoAdults.getSelectionModel().select("1");

        fxHotelsNoChildren.getItems().removeAll(fxHotelsNoChildren.getItems());
        fxHotelsNoChildren.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        fxHotelsNoChildren.getSelectionModel().select("0");
    }

    public void setFXFlightsChoiceBox() {
        //Setti inn virkni fyrir choicebox í Flights tab, kannski er betri leið fyrir það samt.
        fxFlightsNoPassengers.getItems().removeAll(fxFlightsNoPassengers.getItems());
        fxFlightsNoPassengers.getItems().addAll("1 passenger", "2 passengers", "3 passengers", "4 passengers", "5 passengers", "6 passengers", "7 passengers", "8 passengers", "9 passengers");
        fxFlightsNoPassengers.getSelectionModel().select("1 passenger");

        fxOneWayRoundTrip.getItems().removeAll(fxOneWayRoundTrip.getItems());
        fxOneWayRoundTrip.getItems().addAll( "Round trip", "One way trip");
        fxOneWayRoundTrip.getSelectionModel().select("Round trip");
    }

    public void setFXDayTripsChoiceBox() {
        //virkni fyrir combo box í Day Trips tab
        fxDayTripsArrivalTime.getItems().removeAll(fxDayTripsArrivalTime.getItems());
        fxDayTripsArrivalTime.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
        fxDayTripsArrivalTime.getSelectionModel().select("8:00");
        fxDayTripsArrivalTime.setVisibleRowCount(5);

        fxDayTripsDuration.getItems().removeAll(fxDayTripsDuration.getItems());
        fxDayTripsDuration.getItems().addAll("<1:00","1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", ">10:00");
        fxDayTripsDuration.getSelectionModel().select("<1:00");
        fxDayTripsDuration.setVisibleRowCount(5);
    }

}
