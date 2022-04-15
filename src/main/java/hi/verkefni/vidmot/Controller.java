package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

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
    public ChoiceBox fxHotelsNoChildren;
    @FXML
    private ChoiceBox fxFlightsNoPassengers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setti inn virkni fyrir choicebox í Flights tab
        setFXHotelChoiceBox();

        //virkni fyrir choice box í Hotels tab
        setFXHotelChoiceBox();
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

}
