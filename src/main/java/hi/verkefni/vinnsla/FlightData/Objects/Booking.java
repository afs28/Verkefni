package hi.verkefni.vinnsla.FlightData.Objects;
import java.util.Date;

public class Booking {
    private Seat seat;
    private Flight flight;
    private Customer customer;
    private boolean pillow;
    private int id;
    private Review review;

    public Booking(Seat seat, Flight flight, Customer customer, boolean pillow, int id, Review review) {
        this.seat = seat;
        this.flight = flight;
        this.customer = customer;
        this.pillow = pillow;
        this.id = id;
        this.review = review;
    }


    public void cancelBooking(Booking booking)  {
        this.seat = null;
        this.flight = null;
        this.customer = null;
        this.pillow = false;
        this.id = 0;
        this.review = null;
    }

    public void changeReview(Flight flight, Date date, double rating, String text, Customer customer) {
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
                ", id=" + id +
                ", review=" + review +
                '}';
    }
}

