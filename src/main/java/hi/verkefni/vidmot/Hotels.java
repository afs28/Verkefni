package hi.verkefni.vidmot;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Hotels {
    //variables
    private String bookHotel;
    private String filterHotels;
    private String chooseHotel;
    private Integer hotelsPrice;
    private String changeHotel;
    private Date hotelDeparture;
    private Date hotelArrival;
    private TimeUnit checkInTime;
    private TimeUnit checkOutTime;
    private String hotelLocation;
    private Integer guestQuantity;
    private Integer guestAdult;
    private Integer guestChild;

    //constructor
    public Hotels(String bookHotel, String filterHotels, String chooseHotel, Integer hotelsPrice, String changeHotel, Date hotelDeparture, Date hotelArrival, TimeUnit checkInTime, TimeUnit checkOutTime, String hotelLocation, Integer guestQuantity, Integer guestAdult, Integer guestChild) {
        this.bookHotel = bookHotel;
        this.filterHotels = filterHotels;
        this.chooseHotel = chooseHotel;
        this.hotelsPrice = hotelsPrice;
        this.changeHotel = changeHotel;
        this.hotelDeparture = hotelDeparture;
        this.hotelArrival = hotelArrival;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.hotelLocation = hotelLocation;
        this.guestQuantity = guestQuantity;
        this.guestAdult = guestAdult;
        this.guestChild = guestChild;
    }

    //getters
    public String getBookHotel() {
        return bookHotel;
    }

    public String getFilterHotels() {
        return filterHotels;
    }

    public String getChooseHotel() {
        return chooseHotel;
    }

    public Integer getHotelsPrice() {
        return hotelsPrice;
    }

    public String getChangeHotel() {
        return changeHotel;
    }

    public Date getHotelDeparture() {
        return hotelDeparture;
    }

    public Date getHotelArrival() {
        return hotelArrival;
    }

    public TimeUnit getCheckInTime() {
        return checkInTime;
    }

    public TimeUnit getCheckOutTime() {
        return checkOutTime;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public Integer getGuestQuantity() {
        return guestQuantity;
    }

    public Integer getGuestAdult() {
        return guestAdult;
    }

    public Integer getGuestChild() {
        return guestChild;
    }
}
