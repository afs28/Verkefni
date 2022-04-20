package hi.verkefni.vidmot;

import javafx.collections.FXCollections;
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
    public Button fxAcountNextButton;
    @FXML
    public ChoiceBox fxFlightsFrom;
    @FXML
    public ChoiceBox fxFlightsTo;
    @FXML
    public TableView fxHotelTable;
    @FXML
    private ChoiceBox<String> fxFlightsNoPassengers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //choice og comboboxes
        setFXFlightsBox();
        setFXHotelsBox();
        setFXDayTripsBox();

        //back og next takkar
        fxFlightsBackButton.setOnAction(fxFlightsBackButtonEvent);
        fxFlightsNextButton.setOnAction(fxFlightsNextButtonEvent);
        fxHotelsBackButton.setOnAction(fxHotelsBackButtonEvent);
        fxHotelsNextButton.setOnAction(fxHotelsNextButtonEvent);
        fxDayTripsBackButton.setOnAction(fxDayTripsBackButtonEvent);
        fxDayTripsNextButton.setOnAction(fxDayTripsNextButtonEvent);
        fxOrderBackButton.setOnAction(fxOrderSummaryBackButtonEvent);
        fxAcountNextButton.setOnAction(fxMyOrderNextButtonEvent);
    }


    public void setFXFlightsBox() {
        //Setti inn virkni fyrir choicebox í Flights tab, kannski er betri leið fyrir það samt.
        fxFlightsNoPassengers.setItems(FXCollections.observableArrayList("","1 passenger", "2 passengers", "3 passengers",
                "4 passengers", "5 passengers", "6 passengers", "7 passengers", "8 passengers", "9 passengers"));
        fxFlightsNoPassengers.getSelectionModel().select("");

        fxOneWayRoundTrip.setItems(FXCollections.observableArrayList( "", "Round trip", "One way trip"));
        fxOneWayRoundTrip.getSelectionModel().select("");

        //From
        fxFlightsFrom.setItems(FXCollections.observableArrayList("", "Reykjavík", "Egilsstaðir", "Akureyri", "Keflavík", "Húsavík"));
        fxFlightsFrom.getSelectionModel().select("");

        //To
        fxFlightsTo.setItems(FXCollections.observableArrayList("", "Reykjavík", "Egilsstaðir", "Akureyri", "Keflavík", "Húsavík"));
        fxFlightsTo.getSelectionModel().select("");
    }

    public void setFXHotelsBox() {
        fxCapacity.setItems(FXCollections.observableArrayList("","1", "2","3","4","5","6"));
        fxCapacity.getSelectionModel().select("");

        fxTypeOfRoom.setItems(FXCollections.observableArrayList("","Luxury", "Economy"));
        fxTypeOfRoom.getSelectionModel().select("");

        fxLengthOfStay.setItems(FXCollections.observableArrayList("", "1 night", "2 nights", "3 nights", "4 nights", "5 nights", "6 nights", "7 nights", "8 nights",
                "9 nights", "10 nights", "11 nights", "12 nights", "13 nights", "14 nights", "15 nights", "16 nights", "17 nights", "18 nights",
                "19 nights", "20 nights", "21 nights", "22 nights", "23 nights", "24 nights", "25 nights", "26 nights"));
        fxLengthOfStay.getSelectionModel().select("");
        fxLengthOfStay.setVisibleRowCount(5);

        fxHotelLocation.setItems(FXCollections.observableArrayList("", "S", "N", "V", "A"));
        fxHotelLocation.getSelectionModel().select("");
    }

    public void setFXDayTripsBox() {
        fxActivity.setItems(FXCollections.observableArrayList("", "Fjallganga", "Sigling", "Skíði", "Köfun"));
        fxActivity.getSelectionModel().select("");

        fxLocation.setItems(FXCollections.observableArrayList("", "S", "N", "V", "A"));
        fxLocation.getSelectionModel().select("");

        fxLanguage.setItems(FXCollections.observableArrayList("", "Íslenska", "Enska"));
        fxLanguage.getSelectionModel().select("");
    }


    //Event handler fyrir fxFlightsBackButton
    EventHandler<ActionEvent> fxMyOrderNextButtonEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            fxTabPane.getSelectionModel().select(fxFlightsTab);
        }
    };

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
