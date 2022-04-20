package hi.verkefni.vinnsla.FlightData.Objects;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class Flight implements Comparable<Flight>{
    private String departure;
    private String arrival;
    private LocalDate dateTime;
    private final String flightNumber;
    private double cost;
    private int seatsLeft;
    private String aircraft;
    private int bookedPillows;
    private boolean[][] isBookedSeat;
    private double avrRating;
    private boolean food;
    private boolean entertainment;

    public Flight(String departure, String arrival, LocalDate dateTime, String flightNumber, double cost, int seatsLeft, double avrRating, String aircraft, boolean hasFood, boolean hasEntertainment){
        this.departure = departure;
        this.arrival = arrival;
        this.dateTime = dateTime;
        this.flightNumber = flightNumber;
        this.cost = cost;
        this.seatsLeft = seatsLeft;
        this.aircraft = aircraft;
        this.bookedPillows = 0;
        this.isBookedSeat = new boolean[13][4];
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 4; j++){
                isBookedSeat[i][j] = false;
            }
        }
        this.avrRating = avrRating;
        this.food = hasFood;
        this.entertainment = hasEntertainment;
    }

    public boolean areSeatsLeft(){
        if(seatsLeft > 0) return true;
        else return false;
    }

    public boolean isBooked(Seat seat){
        int row = seat.getRow();
        int seatChar = seat.seatCharToInt();
        return isBookedSeat[row][seatChar];
    }

    public void bookSeat(Seat seat) {
        int row = seat.getRow();
        int seatChar = seat.seatCharToInt();
        if(!isBooked(seat)){
            isBookedSeat[row][seatChar] = true;
        }
    }

    public void freeSeat(Seat seat) {
        int row = seat.getRow();
        int seatChar = seat.seatCharToInt();
        isBookedSeat[row][seatChar] = false;
    }

    public void orderPillow(){
        bookedPillows++;
    }

    public void setDateTime(LocalDate dateTime){
        this.dateTime = dateTime;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public void setAircraft(String aircraft){
        this.aircraft = aircraft;
    }

    public void setAvrRating(double rating){
        this.avrRating = rating;
    }

    public void setFood(boolean bool){
        this.food = bool;
    }

    public String getDeparture(){
        return this.departure;
    }

    public String getArrival(){
        return this.arrival;
    }

    public LocalDate getDateTime(){
        return this.dateTime;
    }

    public String getFlightNumber(){
        return this.flightNumber;
    }

    public double getCost(){
        return this.cost;
    }

    public int getSeatsLeft(){
        return this.seatsLeft;
    }

    public void setSeatsLeft(int seatsLeft) { this.seatsLeft = seatsLeft; }

    public String getAircraft(){
        return this.aircraft;
    }

    public int getBookedPillows(){
        return this.bookedPillows;
    }

    public boolean[][] getIsBookedSeat(){
        return this.isBookedSeat;
    }

    public double getAvrRating(){
        return this.avrRating;
    }

    public boolean getFood(){
        return this.food;
    }

    public boolean getEntertainment(){
        return this.entertainment;
    }

    // Higher rating > lower rating
    @Override
    public int compareTo(Flight other) {
        // TODO Auto-generated method stub
        if (this.avrRating > other.avrRating) return 1;
        else if (this.avrRating < other.avrRating) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", dateTime=" + dateTime +
                ", flightNumber='" + flightNumber + '\'' +
                ", cost=" + cost +
                ", seatsLeft=" + seatsLeft +
                ", aircraft='" + aircraft + '\'' +
                ", bookedPillows=" + bookedPillows +
                ", avrRating=" + avrRating +
                ", food=" + food +
                ", entertainment=" + entertainment +
                '}';
    }


}