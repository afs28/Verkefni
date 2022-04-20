package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    public TabPane fxTabPane;
    @FXML
    public Button fxFlightsBackButton;
    @FXML
    public Button fxFlightsNextButton;
    @FXML
    public Tab fxMyAccountTab;
    @FXML
    public Tab fxFlightsTab;
    @FXML
    private ChoiceBox<String> fxFlightsNoPassengers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFXFlightsChoiceBox();
    }


    public void setFXFlightsChoiceBox() {
        //Setti inn virkni fyrir choicebox í Flights tab, kannski er betri leið fyrir það samt.
        fxFlightsNoPassengers.getItems().removeAll(fxFlightsNoPassengers.getItems());
        fxFlightsNoPassengers.getItems().addAll("","1 passenger", "2 passengers", "3 passengers", "4 passengers", "5 passengers", "6 passengers", "7 passengers", "8 passengers", "9 passengers");
        fxFlightsNoPassengers.getSelectionModel().select("");

        fxOneWayRoundTrip.getItems().removeAll(fxOneWayRoundTrip.getItems());
        fxOneWayRoundTrip.getItems().addAll( "", "Round trip", "One way trip");
        fxOneWayRoundTrip.getSelectionModel().select("");
    }



}
