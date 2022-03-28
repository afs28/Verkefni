package hi.verkefni.vidmot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Flights {

    /* TODO
    Hreinsa til, færa inní controller sem á við
     */

    //variables
    private String bookFlights; // controller
    private String filterFlights; // controller
    private String chooseFlights; // controller
    private int flightsPrice;
    private String departureLocation;
    private String destinationLocation;
    // private String returnLocation;
    private LocalTime departureTime;
    private LocalTime returnTime;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int tripDuration;

    //constructor
    public Flights(int flightsPrice, String departureLocation, String destinationLocation, LocalTime departureTime, LocalDate departureDate, int tripDuration) {
        // this.bookFlights = bookFlights;
        // this.filterFlights = filterFlights;
        // this.chooseFlights = chooseFlights;
        this.flightsPrice = flightsPrice;
        this.departureLocation = departureLocation;
        // this.returnLocation = returnLocation;
        this.destinationLocation = destinationLocation;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.tripDuration = tripDuration;


    }

    //getters

    public int getTripDuration() {
        return tripDuration;
    }

    public String getBookFlights() {
        return bookFlights;
    }

    public String getFilterFlights() {
        return filterFlights;
    }

    public String getChooseFlights() {
        return chooseFlights;
    }

    public Integer getFlightsPrice() {
        return flightsPrice;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

     /* public String getReturnLocation() {
       return returnLocation;
     } */

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    /* public TimeUnit getReturnTime() {
        return returnTime;
    } */

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /* public Date getReturnDate() {
        return returnDate;
    } */
}
