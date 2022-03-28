package hi.verkefni.vidmot;

import java.util.Date;

public class DayTrips {
    //variables
    private String bookDayTrip;
    private String filterDayTrips;
    private String chooseDayTrips;
    private Integer dayTripsPrice;
    private String changeDayTrip;
    private Date dayTripDeparture;
    private Date dayTripArrival;
    private Integer dayTripDuration;

    /* TODO
    Þarf að hreinsa til í constructornum, taka hluti út og setja það sem við á í controllers.
    */

    //constructor
    public DayTrips(String bookDayTrip, String filterDayTrips, String chooseDayTrips, Integer dayTripsPrice, String changeDayTrip, Date dayTripDeparture, Date dayTripArrival, Integer dayTripDuration) {
        this.bookDayTrip = bookDayTrip;
        this.filterDayTrips = filterDayTrips;
        this.chooseDayTrips = chooseDayTrips;
        this.dayTripsPrice = dayTripsPrice;
        this.changeDayTrip = changeDayTrip;
        this.dayTripDeparture = dayTripDeparture;
        this.dayTripArrival = dayTripArrival;
        this.dayTripDuration = dayTripDuration;
    }

    //getters
    public String getBookDayTrip() {
        return bookDayTrip;
    }

    public String getFilterDayTrips() {
        return filterDayTrips;
    }

    public String getChooseDayTrips() {
        return chooseDayTrips;
    }

    public Integer getDayTripsPrice() {
        return dayTripsPrice;
    }

    public String getChangeDayTrip() {
        return changeDayTrip;
    }

    public Date getDayTripDeparture() {
        return dayTripDeparture;
    }

    public Date getDayTripArrival() {
        return dayTripArrival;
    }

    public Integer getDayTripDuration() {
        return dayTripDuration;
    }
}
