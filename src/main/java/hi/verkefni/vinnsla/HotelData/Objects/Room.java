package hi.verkefni.vinnsla.HotelData.Objects;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

//Holds information about the rooms. Needed for hotel.

public class Room implements Serializable {

    private String roomID; // id to distinguish between rooms
    private String type; //f.x luxus or economy 
    private int capacity; // how many people it can fit
    private int price; // the cost for reservation of 1 night
    
    //needed for booking system.
    private boolean[] january; 
    private boolean[] february;
    private boolean[] march;
    private boolean[] april;
    private boolean[] may;
    private boolean[] june;
    private boolean[] july;
    private boolean[] august;
    private boolean[] september;
    private boolean[] october;
    private boolean[] november;
    private boolean[] december;
    
    
    //Constructor
    public Room(String type, int capacity, int price) {
        this.setRoomID(getRandomString());
        this.setType(type);
        this.setCapacity(capacity);
        this.setPrice(price);
        this.setJanuary(new boolean[31]);
        this.setFebruary(new boolean [28]);
        this.setMarch(new boolean [31]);
        this.setApril(new boolean [30]);
        this.setMay(new boolean [31]);
        this.setJune(new boolean [30]);
        this.setJuly(new boolean [31]);
        this.setAugust(new boolean [31]);
        this.setSeptember(new boolean [30]);
        this.setOctober(new boolean [31]);
        this.setNovember(new boolean [30]);
        this.setDecember(new boolean [31]);
    }
    //Getters and setters
    public String getRoomID() {
        return roomID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean[] getJanuary() {
		return january;
	}
	public void setJanuary(boolean[] january) {
		this.january = january;
	}
	public boolean[] getFebruary() {
		return february;
	}
	public void setFebruary(boolean[] february) {
		this.february = february;
	}
	public boolean[] getMarch() {
		return march;
	}
	public void setMarch(boolean[] march) {
		this.march = march;
	}
	public boolean[] getApril() {
		return april;
	}
	public void setApril(boolean[] april) {
		this.april = april;
	}
	public boolean[] getMay() {
		return may;
	}
	public void setMay(boolean[] may) {
		this.may = may;
	}
	public boolean[] getJune() {
		return june;
	}
	public void setJune(boolean[] june) {
		this.june = june;
	}
	public boolean[] getJuly() {
		return july;
	}
	public void setJuly(boolean[] july) {
		this.july = july;
	}
	public boolean[] getAugust() {
		return august;
	}
	public void setAugust(boolean[] august) {
		this.august = august;
	}
	public boolean[] getSeptember() {
		return september;
	}
	public void setSeptember(boolean[] september) {
		this.september = september;
	}
	public boolean[] getOctober() {
		return october;
	}
	public void setOctober(boolean[] october) {
		this.october = october;
	}
	public boolean[] getNovember() {
		return november;
	}
	public void setNovember(boolean[] november) {
		this.november = november;
	}
	public boolean[] getDecember() {
		return december;
	}
	public void setDecember(boolean[] december) {
		this.december = december;
	}
	
	///////////////////////////////////////////////	///////////////////////////////////////////////
	
	
	//Random String generator for RoomID
	
	public static String getRandomString(){
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 8;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }

        String a = sb.toString();

        System.out.println(a + " = roomID");

        return a;
    }
}
