package hi.verkefni.vinnsla.FlightData.Controllers;
import java.util.Date;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

import java.util.ArrayList;

public class SearchController { //wip
    private static ArrayList<Flight> flights;
    private static FlightDB fdb;
    private static String s;
    static BookingController bc = new BookingController();
    static CustomerController cc = new CustomerController();
    static FlightController fc = new FlightController();

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Flight> f = filterDeparture("Akureyri");
        ArrayList<Flight> x = filterEnt(true);
        ArrayList<Flight> y = filterAvrRating();
        for (Flight o : y) System.out.println(o);

        /*
        Seat seat = new Seat(1, 'r');
        Flight f = fdb.GetFlightByFlightNumber("000CE");
        Customer c = cc.GetCustomerBySsno(123456789);
        Review r = new Review(f, new Date(), 5.0, "Geggjað", c);
        bc.CreateNewBooking(seat, f, c, true, 11, r);

         */
    }

    public SearchController() {
        fdb = new FlightDB();
        flights = fc.GetAllFlights();
    }

    /**
     *
     * @param low lower bound of flight cost
     * @param high upper bound of flight cost
     * @return an ArrayList of Flight objects with prices from low - high
     */
    public ArrayList<Flight> filterCost(int low, int high) {
        if (s == null) s = "SELECT * FROM flights WHERE cost BETWEEN " + low + " AND " + high;
        else s += " AND cost BETWEEN " + low + " AND " + high;
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     * @param departure Departure airport
     * @param destination Destination airport
     * @return ArrayList of Flight objects going from departure airport to destination airport
     */
    public ArrayList<Flight> filterDepartureAndArrival(String departure, String destination) {
        if (s == null) s = "SELECT * FROM flights WHERE departure='" + departure + "' AND arrival='" + destination + "'";
        else s += " AND departure='" + departure + "' AND arrival='" + destination + "'";
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     * @param departureAirport Departure airport
     * @return an ArrayList of Flight objects where flights are from departure airport
     */
    public static ArrayList<Flight> filterDeparture(String departureAirport) {
        if (s == null) s = "SELECT * FROM flights WHERE departure = '" + departureAirport + "'";
        else s += " AND departure='" + departureAirport + "'";
        fdb = new FlightDB();
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     * @param destinationAirport Destination airport
     * @return an ArrayList of Flight objects with flights going to the destination airport
     */
    public ArrayList<Flight> filterDestination(String destinationAirport) {
        if (s == null) s = "SELECT * FROM flights WHERE arrival='" + destinationAirport + "'";
        else s += " AND arrival='" + destinationAirport + "'";
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     * @param date
     * @return
     */
    // TODO Bíða með þetta þar sem það er vesen að parsa date úr sqlite
    public ArrayList<Flight> filterDate(Date date) {
        if(s == ""){
            s = "SELECT flight FROM flights WHERE date ='" + date + "'";
        }
        else{
            this.s = s + " AND date = " + date;
        }
        flights = fc.search(s);
        ArrayList<Flight> f = null;
        return f;
    }

    /**
     *
     * @param hasEntertainmentOnBoard true if searching for flights with entertainment, false otherwise
     * @return an ArrayList of Flight objects that have entertainment on board if true, and an ArrayList of Flight objects that don't have entertainment on board if false.
     */
    public static ArrayList<Flight> filterEnt(boolean hasEntertainmentOnBoard) {
        int bool;
        if (hasEntertainmentOnBoard) bool = 1;
        else bool = 0;
        if (s == null) s = "SELECT * FROM flights WHERE hasEntertainment = '" + bool + "'";
        else s += " and hasEntertainment = '" + bool + "'";
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     * @param hasFoodOnBoard true if searching for flights with food services, false otherwise
     * @return an ArrayList of Flight objects that have food services on board if true, and an ArrayList of Flight objects that don't have food services on board if false.
     */
    public ArrayList<Flight> filterFood(boolean hasFoodOnBoard) {
        int bool;
        if (hasFoodOnBoard) bool = 1;
        else bool = 0;
        if (s == null) s = s = "SELECT * FROM flights WHERE food='" + bool + "'";
        else s += " AND food='" + bool + "'";
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     * @return an ArrayList of Flight objects that have rating ???
     */
    public static ArrayList<Flight> filterAvrRating() {
        if(s == null){
            s = "SELECT * FROM flights ORDER BY rating";
        }
        else{
            s = s + " ORDER BY rating";
        }
        flights = fc.search(s);
        return flights;
    }

    /**
     *
     */
    public void removeFilters() {
        String s = null;
        String str = "SELECT * FROM flights";
        flights = fc.search(str);
    }


    /**
     *
     * @return
     */
    public ArrayList<Flight> getFlights(){
        return this.flights;
    }
}
