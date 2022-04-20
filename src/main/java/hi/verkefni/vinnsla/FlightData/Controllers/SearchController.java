package hi.verkefni.vinnsla.FlightData.Controllers;
import java.time.LocalDate;
import java.util.Date;

import hi.verkefni.vinnsla.FlightData.Database.FlightDB;
import hi.verkefni.vinnsla.FlightData.Objects.*;

import java.util.ArrayList;

public class SearchController { //wip
    private ArrayList<Flight> flights;
    private FlightDB fdb;
    private String s;
    static BookingController bc = new BookingController();
    static CustomerController cc = new CustomerController();
    static FlightController fc = new FlightController();

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

    public ArrayList<Flight> filterCost(ArrayList<Flight> flightsToFilter, int low, int high) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getCost() >= low && f.getCost() <= high) filtered.add(f);
        }
        return filtered;
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

    public ArrayList<Flight> filterDepartureAndArrival(ArrayList<Flight> flightsToFilter, String departureAirport, String arrivalAirport) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getDeparture() == departureAirport && f.getArrival() == arrivalAirport) filtered.add(f);
        }
        return filtered;
    }

    /**
     *
     * @param departureAirport Departure airport
     * @return an ArrayList of Flight objects where flights are from departure airport
     */
    public ArrayList<Flight> filterDeparture(String departureAirport) {
        if (s == null) s = "SELECT * FROM flights WHERE departure = '" + departureAirport + "'";
        else s += " AND departure='" + departureAirport + "'";
        fdb = new FlightDB();
        flights = fc.search(s);
        return flights;
    }

    public ArrayList<Flight> filterDeparture(ArrayList<Flight> flightsToFilter, String departureAirport) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getDeparture() == departureAirport) filtered.add(f);
        }
        return filtered;
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

    public ArrayList<Flight> filterDestination(ArrayList<Flight> flightsToFilter, String destinationAirport) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getArrival() == destinationAirport) filtered.add(f);
        }
        return filtered;
    }

    /**
     *
     * @param date
     * @return
     */
    public ArrayList<Flight> filterDate(LocalDate date) {
        if(s == null) s = "SELECT * FROM flights WHERE date ='" + date + "'";
        else s = s + " AND date = " + date;
        flights = fc.search(s);
        return flights;
    }

    public ArrayList<Flight> filterDate(ArrayList<Flight> flightsToFilter, LocalDate date) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getDateTime() == date) filtered.add(f);
        }
        return filtered;
    }

    /**
     *
     * @param hasEntertainmentOnBoard true if searching for flights with entertainment, false otherwise
     * @return an ArrayList of Flight objects that have entertainment on board if true, and an ArrayList of Flight objects that don't have entertainment on board if false.
     */
    public ArrayList<Flight> filterEnt(boolean hasEntertainmentOnBoard) {
        int bool;
        if (hasEntertainmentOnBoard) bool = 1;
        else bool = 0;
        if (s == null) s = "SELECT * FROM flights WHERE hasEntertainment = '" + bool + "'";
        else s += " and hasEntertainment = '" + bool + "'";
        flights = fc.search(s);
        return flights;
    }

    public ArrayList<Flight> filterEnt(ArrayList<Flight> flightsToFilter, boolean hasEntertainmentOnBoard) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getEntertainment() == hasEntertainmentOnBoard) filtered.add(f);
        }
        return filtered;
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

    public ArrayList<Flight> filterFood(ArrayList<Flight> flightsToFilter, boolean hasFoodOnBoard) {
        ArrayList<Flight> filtered = new ArrayList<>();
        for (Flight f : flightsToFilter) {
            if (f.getFood() == hasFoodOnBoard) filtered.add(f);
        }
        return filtered;
    }

    /**
     *
     * @return an ArrayList of Flight objects that have rating ???
     */
    public ArrayList<Flight> filterAvrRating() {
        if(s == null){
            s = "SELECT * FROM flights ORDER BY rating";
        }
        else{
            s = s + " ORDER BY rating";
        }
        flights = fc.search(s);
        return flights;
    }

    public void removeFilters() {
        String s = null;
        String str = "SELECT * FROM flights";
        flights = fc.search(str);
    }

    public ArrayList<Flight> getFlights(){
        return this.flights;
    }
}
