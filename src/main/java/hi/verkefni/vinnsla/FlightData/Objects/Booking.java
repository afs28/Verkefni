package hi.verkefni.vinnsla.FlightData.Objects;
import java.time.LocalDate;

public class Booking {
    private Seat seat;
    private Flight flight;
    private Customer customer;
    private boolean pillow;
    private int id;
    private final int numberOfPassengers;
    private Review review;

    public Booking(Seat seat, Flight flight, Customer customer, boolean pillow, Review review, int numberOfPassengers) {
        this.seat = seat;
        this.flight = flight;
        this.customer = customer;
        this.pillow = pillow;
        this.review = review;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void cancelBooking(Booking booking)  {
        this.seat = null;
        this.flight = null;
        this.customer = null;
        this.pillow = false;
        this.id = 0;
        this.review = null;
    }

    public void changeReview(Flight flight, LocalDate date, double rating, String text, Customer customer) {
        this.review = new Review(flight, date, rating, text, customer);
    }

    public Seat getSeat(){
        return this.seat;
    }

    public Flight getFlight(){
        return this.flight;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public boolean getPillow(){
        return this.pillow;
    }

    public int getNumberOfPassengers() { return this.numberOfPassengers; }

    public int getId(){
        return this.id;
    }

    public Review getReview(){
        return this.review;
    }

    public void setSeat(Seat seat){
        this.seat = seat;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setPillow(boolean pillow){
        this.pillow = pillow;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setReview(Review review){
        this.review = review;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "seat=" + seat +
                ", flight=" + flight +
                ", customer=" + customer +
                ", pillow=" + pillow +
                ", review=" + review +
                '}';
    }
}

