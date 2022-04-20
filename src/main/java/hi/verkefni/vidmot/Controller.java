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
    public Tab fxHotelsTab;
    @FXML
    public ChoiceBox fxCapacity;
    @FXML
    public ChoiceBox fxTypeOfRoom;
    @FXML
    public DatePicker fxHotelArrivalDate;
    @FXML
    public Button fxHotelsSearch;
    @FXML
    public ChoiceBox fxHotelLocation;
    @FXML
    public ComboBox fxLengthOfStay;
    @FXML
    public Button fxHotelsBackButton;
    @FXML
    public Button fxHotelsNextButton;
    @FXML
    public Tab fxDayTripsTab;
    @FXML
    public ComboBox fxActivity;
    @FXML
    public ComboBox fxLocation;
    @FXML
    public ComboBox fxLanguage;
    @FXML
    public DatePicker fxDayTripDate;
    @FXML
    public Button fxDayTripsBackButton;
    @FXML
    public Button fxDayTripsNextButton;
    @FXML
    public Tab fxOrderSummaryTab;
    @FXML
    public Label fxTotalPrice;
    @FXML
    public Button fxOrderBackButton;
    @FXML
    public Button fxOrderButton;
    @FXML
    private ChoiceBox<String> fxFlightsNoPassengers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //choice og comboboxes
        setFXFlightsChoiceBox();

        //back og next takkar
        fxFlightsBackButton.setOnAction(fxFlightsBackButtonEvent);
        fxFlightsNextButton.setOnAction(fxFlightsNextButtonEvent);
        fxHotelsBackButton.setOnAction(fxHotelsBackButtonEvent);
        fxHotelsNextButton.setOnAction(fxHotelsNextButtonEvent);
        fxDayTripsBackButton.setOnAction(fxDayTripsBackButtonEvent);
        fxDayTripsNextButton.setOnAction(fxDayTripsNextButtonEvent);
        fxOrderBackButton.setOnAction(fxOrderSummaryBackButtonEvent);
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

    //Event handler fyrir fxFlightsBackButton
    EventHandler<ActionEvent> fxFlightsBackButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxMyAccountTab);
        }
    };

    //Event handler fyrir fxFlightsNextButton
    EventHandler<ActionEvent> fxFlightsNextButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxHotelsTab);
        }
    };

    //Event handler fyrir fxHotelsBackButton
    EventHandler<ActionEvent> fxHotelsBackButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxFlightsTab);
        }
    };

    //Event handler fyrir fxHotelsNextButton
    EventHandler<ActionEvent> fxHotelsNextButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxDayTripsTab);
        }
    };

    //Event handler fyrir fxDayTripsBackButton
    EventHandler<ActionEvent> fxDayTripsBackButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxHotelsTab);
        }
    };

    //event handler fyrir fxDayTripsNextButton
    EventHandler<ActionEvent> fxDayTripsNextButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxOrderSummaryTab);
        }
    };

    //event handler fyrir fxOrderBackButton
    EventHandler<ActionEvent> fxOrderSummaryBackButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxDayTripsTab);
        }
    };




}
