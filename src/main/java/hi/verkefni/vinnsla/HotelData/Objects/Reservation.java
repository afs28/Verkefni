package hi.verkefni.vinnsla.HotelData.Objects;

import java.util.Random;
import java.io.Serializable;

// Class to hold informations about reservations. Needed for User and Hotel classes

public class Reservation implements Serializable {

	private Hotel hotel; // hotel that this reservation is for
	private String reservationNum; //Just a random number. Basically id for reservation to distinguish between many reservations.
	private int inDay; // date of arrival
	private int inMonth; // month of arrival
	private int numGuests; //number of guests
	private int numDays; //number of days staying in the hotel
	private User user; // the user who made the reservation
	private Room bookedroom; //room that was booked
	
	//Constructor
	public Reservation(Hotel hotel,int inday,int inmonth,int numdays, int numGuests,
			User user, Room bookedroom) {
		this.setHotel(hotel);
		this.setReservationNum(getRandomNumber()); //Assign random generated number
		this.inDay =inday;
		this.inMonth = inmonth;
		this.setNumDays(numdays);
		this.setNumGuests(numGuests);
		this.setUser(user);
		this.setBookedroom(bookedroom);
		
	}

	//Getters and setters
	
	public String getReservationNum() {
		return reservationNum;
	}
	public void setReservationNum(String string) {
		this.reservationNum = string;
	}
	public int getNumGuests() {
		return numGuests;
	}
	public void setNumGuests(int numGuests) {
		this.numGuests = numGuests;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public Room getBookedroom() {
		return bookedroom;
	}

	public void setBookedroom(Room bookedroom) {
		this.bookedroom = bookedroom;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	////////////////////////////////

	
	//random number generator. Its a String made of numbers.
	public static String getRandomNumber(){
        Random rand = new Random();
        String svar = "";

        int a1 = rand.nextInt((9 - 0) + 1) + 0;
        int a2 = rand.nextInt((9 - 0) + 1) + 0;
        int a3 = rand.nextInt((9 - 0) + 1) + 0;
        int a4 = rand.nextInt((9 - 0) + 1) + 0;
        int a5 = rand.nextInt((9 - 0) + 1) + 0;
        int a6 = rand.nextInt((9 - 0) + 1) + 0;
        int a7 = rand.nextInt((9 - 0) + 1) + 0;
        int a8 = rand.nextInt((9 - 0) + 1) + 0;
        int a9 = rand.nextInt((9 - 0) + 1) + 0;
        int a10 = rand.nextInt((9 - 0) + 1) + 0;
        int a11 = rand.nextInt((9 - 0) + 1) + 0;
        int a12 = rand.nextInt((9 - 0) + 1) + 0;
        int a13 = rand.nextInt((9 - 0) + 1) + 0;
        int a14 = rand.nextInt((9 - 0) + 1) + 0;
        int a15 = rand.nextInt((9 - 0) + 1) + 0;
        int a16 = rand.nextInt((9 - 0) + 1) + 0;

        svar = a1+""+a2+""+a3+""+a4+""+a5+""+a6+""+a7+""+a8+""+a9+""+a10+""+a11+""+a12+""+a13+""+a14+""+a15+""+a16;

        System.out.println(svar + ": reservationNUM");

        return svar;
    }
	
	
}
