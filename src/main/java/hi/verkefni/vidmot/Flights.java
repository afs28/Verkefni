package hi.verkefni.vidmot;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Flights {
    //variables
    private String bookFlights;
    private String filterFlights;
    private String chooseFlights;
    private Integer flightsPrice;
    private String departureLocation;
    private String returnLocation;
    private TimeUnit departureTime;
    private TimeUnit returnTime;
    private Date departureDate;
    private Date returnDate;

    //constructor
    public Flights(String bookFlights, String filterFlights, String chooseFlights, Integer flightsPrice, String departureLocation, String returnLocation, TimeUnit departureTime, TimeUnit returnTime, Date departureDate, Date returnDate) {
        this.bookFlights = bookFlights;
        this.filterFlights = filterFlights;
        this.chooseFlights = chooseFlights;
        this.flightsPrice = flightsPrice;
        this.departureLocation = departureLocation;
        this.returnLocation = returnLocation;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    //getters
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

    public String getReturnLocation() {
        return returnLocation;
    }

    public TimeUnit getDepartureTime() {
        return departureTime;
    }

    public TimeUnit getReturnTime() {
        return returnTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
