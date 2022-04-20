package hi.verkefni.vinnsla.FlightData.Objects;
import java.time.LocalDate;
import java.util.Date;


public class Review implements Comparable<Review>{
    private Flight flight;
    private Customer customer;
    private LocalDate date;
    private double rating;
    private String text;

    public Review(Flight flight, LocalDate date, double rating, String text, Customer customer) {
        this.flight = flight;
        this.date = date;
        this.rating = rating;
        this.text = text;
        this.customer = customer;
    }

    public Flight getFlight(){
        return this.flight;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public Customer getCustomer() { return this.customer; }

    public double getRating(){
        return this.rating;
    }

    public String getText(){
        return this.text;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "flight=" + flight +
                ", date=" + date +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int compareTo(Review other) {
        // TODO Auto-generated method stub
        if (this.rating > other.rating) return 1;
        else if (this.rating < other.rating) return -1;
        return 0;
    }
}