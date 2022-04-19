package hi.verkefni.vinnsla.HotelData.Objects;

import hi.verkefni.vinnsla.HotelData.Controllers.DatabaseController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Bill implements Serializable {

	private int price;
	private String transactionNum;
	private User Payer;
	private Reservation reservation;
	private Hotel hotel;
	
	
	public Bill(String userid, Reservation reservation, String hotelid) throws FileNotFoundException, ClassNotFoundException, IOException {
		DatabaseController DC = new DatabaseController();
		
		String x = getRandomString(16);
		this.setTransactionNum(x);
		this.Payer= DC.findUserbyID(userid);
		this.reservation = reservation;
		this.hotel = DC.findHotelbyID(hotelid);
		
		int amountToPay = reservation.getBookedroom().getPrice();
		amountToPay = amountToPay*reservation.getNumDays();
		this.setPrice(amountToPay);
		
		System.out.println("Bill has been printed: " + x);
		//DC.insertBill(this);
		
	}

	public String getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(String string) {
		this.transactionNum = string;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public User getPayer() {
		return Payer;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public Hotel getHotel() {
		return hotel;
	}
	
	//method for random transactionNum
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

        System.out.println(a);

        return a;
    }
}
