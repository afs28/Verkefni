package hi.verkefni.vinnsla.HotelData.Objects;

import hi.verkefni.vinnsla.HotelData.Controllers.DatabaseController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

//Class to hold informations about the hotels

public class Hotel implements Serializable {
	private String name;
	private String hotelID;
	public bFilter bf; //boolean filters 
	public intFilter intf; //int filters
	
	//not implemented yet
	//private User admin[];
	
	private Review reviews[]; //reviews of the hotel submitted by the users
	private Reservation bookings[]; //history of reservations made be the users for this hotel
	private Room rooms[]; //rooms that this hotel offers
	private double Grade; //calculated from reviews
	private String Location; //f.x. Reykjavik or Egilstadir
	
	//Constructor for list of rooms
	public Hotel(String name, String id, bFilter bf, intFilter intf, Room[] rooms, String location ) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.name = name;
		this.hotelID = getRandomString(16);
		this.bf = bf;
		this.intf = intf;
		this.setRooms(rooms);
		//this.admin = new User[0];
		this.reviews = new Review[0];
		this.bookings = new Reservation[0];
		this.Grade = 0;
		this.Location = location;
		
		//Set the bottom price and top price accordingly to the room prices.
		updatePrices();
	}
	
	//Constructor for one room
	public Hotel(String name, bFilter bf, intFilter intf, Room room, String location ) {
		this.name = name;
		this.hotelID = getRandomString(16);
		this.bf = bf;
		this.intf = intf;
		this.setRoom(room);
		//this.admin = new User[0];
		this.reviews = new Review[0];
		this.bookings = new Reservation[0];
		this.Grade = 0;
		this.Location = location;
		this.intf.setBottomPrice(room.getPrice());
		this.intf.setTopPrice(room.getPrice());
	}
	
	//Method used to update the dates that are reserved for given rooms
	public void update(String s,int i,boolean[] j) {
		Room[] r = this.getRooms();
		int o = -1;
		for(int k = 0; k<r.length;k++) {
			if(r[k].getRoomID().equals(s)) {
				o = k;
			}
		}
		if(i==1) {
			this.getRooms()[o].setJanuary(j);
		}
		if(i==2) {
			this.getRooms()[o].setFebruary(j);
		}
		if(i==3) {
			this.getRooms()[o].setMarch(j);
		}
		if(i==4) {
			this.getRooms()[o].setApril(j);
		}
		if(i==5) {
			this.getRooms()[o].setMay(j);
		}
		if(i==6) {
			this.getRooms()[o].setJune(j);
		}
		if(i==7) {
			this.getRooms()[o].setJuly(j);
		}
		if(i==8) {
			this.getRooms()[o].setAugust(j);
		}
		if(i==9) {
			this.getRooms()[o].setSeptember(j);
		}
		if(i==10) {
			this.getRooms()[o].setOctober(j);
		}
		if(i==11) {
			this.getRooms()[o].setNovember(j);
		}
		if(i==12) {
			this.getRooms()[o].setDecember(j);
		}
		
	}
	
	//just a method to map bFilter over to boolean list
	public boolean[] bFilterVarp() {
		boolean [] meistari = new boolean[9];
		meistari[0]=this.bf.isGym();
		meistari[1]=this.bf.isPets();
		meistari[2]=this.bf.isPool();
		meistari[3]=this.bf.isRestaurant();
		meistari[4]=this.bf.isTerrace();
		meistari[5]=this.bf.isBreakfast();
		meistari[6]=this.bf.isWifi();
		meistari[7]=this.bf.isParking();
		meistari[8]=this.bf.isSmoking();
		return meistari;
	}
	
	//just a method to map intfilter over to int list
	public int[] intFilterVarp() {
		int [] meistari = new int[5];
		meistari[0]=this.intf.getStar();
		meistari[1]=this.intf.getGrade();
		meistari[2]=this.intf.getFromCenterKM();
		meistari[3]=this.intf.getBottomPrice();
		meistari[4]=this.intf.getTopPrice();
		return meistari;
	}
	
	//method that adds reviews to the hotel
	public void addReview(Review r) throws FileNotFoundException, ClassNotFoundException, IOException {
		int i = this.reviews.length;
		Review[] New = new Review[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = this.reviews[j]; 
		}
		New[i] = r;
		this.reviews = New;
		setGrade(); //instantly calculate the grade based on old reviews and the new one
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this); //update the hotel in the database
	}
	
	//method that adds room to the hotel
	public void addRoom(Room r) throws FileNotFoundException, ClassNotFoundException, IOException {
		int i = this.rooms.length;
		Room[] New = new Room[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = this.rooms[j]; 
		}
		New[i] = r;
		this.rooms = New;
		updatePrices(); //calculates the lowest price and highest price that the hotel offers
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this); //update the hotel in the database
	}
	
	//method that adds bookings to the hotel
	public void addBooking(Reservation r) throws FileNotFoundException, ClassNotFoundException, IOException {
		int i = this.bookings.length;
		Reservation[] New = new Reservation[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = this.bookings[j]; 
		}
		New[i] = r;
		this.bookings = New;
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this); //update the hotel in the database
	}

	/*
	 * Not used because the admin feature hasnt been tested well enough
	 * 
	public void addAdmin(User u) throws FileNotFoundException, ClassNotFoundException, IOException {
		int i = this.admin.length;
		User[] New = new User[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = this.admin[j]; 
		}
		New[i] = u;
		this.admin = New;
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this);
	}
	
	public void removeAdmin(User u) throws FileNotFoundException, ClassNotFoundException, IOException {
		int i = this.admin.length;
		User[] New = new User[i-1];
		int store = -1;
		
		for(int j = 0; j < i; j++) {
			if(u == this.admin[j]) {
				store=j;
			}
		}
		for(int k = 0; k<i; k++) {
			if(k != store) {
				New[k] = this.admin[k];
			}
		}
		this.admin = New;
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this);
	}
	*/
	 
	
	/*
	 * Not used but can be helpful in the future if needed
	 * 
	public int getReview(int reviewNum) {
		int size = this.reviews.length;
		Review store = null;
		for(int i = 0; i < size; i++) {
			if(reviewNum == this.reviews[i].getRnum()) {
				store = this.reviews[i];
				break;
			}
		}
		return store;
	}
	
	public Reservation getReservation(int reservationNum) {
		int size = this.bookings.length;
		Reservation store = null;
		for(int i = 0; i < size; i++) {
			if(reservationNum == this.bookings[i].getRnum()) {
				store = this.bookings[i];
				break;
			}
		}
		return store;
	}
	*/
	
	/*
	 * Not fully implemented 
	 * 
	public void removeSpam() throws FileNotFoundException, ClassNotFoundException, IOException {
		int old = this.reviews.length;
		int n = 0;
		for(int i = 0; i<old; i++) {
			if(this.reviews[i].isSpam()==true) {
				n =n+1;
			}
		}
		Review [] listi = new Review[old-n];
		int j = 0;
		for(int k = 0; k<old;k++) {
			if(!this.reviews[k].isSpam()) {
				listi[j] = this.reviews[k];
				j++;
			}
		}
		this.reviews = listi;
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this);
	}
	 */
	
	//Method that calculates the grade of the hotel
	public void setGrade() throws FileNotFoundException, ClassNotFoundException, IOException {
		int i = 0;
		int len = this.reviews.length;
		for(int j =0;j<len;j++) {
			i = i + this.reviews[j].getGrade();
		}
		double loka = i/len;
		this.Grade = loka;
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this); //update the hotel in the database
	
	//Getters and setters	
	}
	public String getID() {
		return this.hotelID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Room[] getRooms() {
		return rooms;
	}
	public void setRooms(Room rooms[]) {
		this.rooms = rooms;
	}
	public void setRoom(Room room) {
		Room[] rooms = new Room[1];
		rooms[0] = room;
		this.rooms = rooms;
	}
	public Review[] getReviews() {
		return this.reviews;
	}
	public double getGrade() {
		return this.Grade;
	}
	
	
	//Method that calculates the lowerst and highest prices offered by the hotel. Used for filters.
	public void updatePrices() throws FileNotFoundException, ClassNotFoundException, IOException {
		int min = -999999999;
		int max = 999999999;
		int len = this.rooms.length;
		for(int i = 0; i<len; i++) {
			if(max<this.rooms[i].getPrice()) {
				max = this.rooms[i].getPrice();
			}
			if(min>this.rooms[i].getPrice()) {
				min = this.rooms[i].getPrice();
			}
		}
		this.intf.setBottomPrice(min);
		this.intf.setTopPrice(max);
		DatabaseController DC = new DatabaseController();
		DC.replaceHotel(this);
	}

	
	//String generator
	 public static String getRandomString(int x){
	        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	        String numbers = "0123456789";

	        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

	        StringBuilder sb = new StringBuilder();
	        Random random = new Random();

	        int length = x;

	        for(int i = 0; i < length; i++) {
	            int index = random.nextInt(alphaNumeric.length());
	            char randomChar = alphaNumeric.charAt(index);
	            sb.append(randomChar);
	        }

	        String a = sb.toString();

	        System.out.println(a + "= HotelId");

	        return a;
	   }
}

