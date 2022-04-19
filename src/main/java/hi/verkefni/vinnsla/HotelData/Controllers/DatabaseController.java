package hi.verkefni.vinnsla.HotelData.Controllers;

import hi.verkefni.vinnsla.HotelData.Objects.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

//Database class that holds all the methods that can change the database

public class DatabaseController {
	

	//GetAll methods for all databases
	
	public Bill[] getallBill() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("BillDB"));
		Bill [] x = (Bill[]) in.readObject();
		return x;
	}
	

	public User[] getallUser() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserDB"));
		User [] x = (User[]) in.readObject();
		return x;
	}
	
	public Hotel[] getallHotel() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("HotelDB"));
		Hotel [] x = (Hotel[]) in.readObject();
		return x;
	}
	
	/* Not used because no database is used for this, rather just stored within the User database
	public creditCard[] getallcreditCard() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("creditCardDB"));
		creditCard [] x = (creditCard[]) in.readObject();
		return x;
	}
	*/
	
	/* Not used because no database is used for this, rather just stored within the User and hotel database
	public Reservation[] getallReservation() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("ReservationDB"));
		Reservation[] x = (Reservation[]) in.readObject();
		return x;
	}
	*/
	
	/* Not used because no database is used for this, rather just stored within the User and hotel database
	public Review[] getAllReview() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("ReviewDB"));
		Review [] x = (Review[]) in.readObject();
		return x;
	}
	*/
	
	/* Not used because no database is used for this, rather just stored within the hotel database
	public Room[] getallRoom() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("RoomDB"));
		Room [] x = (Room[]) in.readObject();
		return x;
	}
	*/
	
	/*Not used because no database is used for this, rather just stored within the User database
	public  Address[] getallAddress() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("AddressDB"));
		Address [] x = (Address[]) in.readObject();
		return x;
	}
	*/
	
	//replace old version of hotel for updated version of Hotel. YET TO BE TESTED
	public void replaceHotel(Hotel h) throws FileNotFoundException, ClassNotFoundException, IOException {
		Hotel[]old = getallHotel();
		for(int i = 0; i<old.length;i++) {
			if(h.getID().equals(old[i].getID())){
				old[i]=h;
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("HotelDB"));
				out.writeObject(old);
			}
		}
			
	}
	//replace old version of user for updated version of User. YET TO BE TESTED
	public void replaceUser(User h) throws FileNotFoundException, ClassNotFoundException, IOException {
		User[]old = getallUser();
		for(int i = 0; i<old.length;i++) {
			if(h.getUserID().equals(old[i].getUserID())){
				old[i]=h;
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UserDB"));
				out.writeObject(old);
			}
		}
			
	}

	
	//Insert methods for all databases
	public void insertHotel(Hotel h) throws FileNotFoundException, IOException, ClassNotFoundException {
		Hotel[] old = getallHotel();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("HotelDB"));
		int i = old.length;
		Hotel[] New = new Hotel[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[i] = h;
		out.writeObject(New);
		
	}
	
	public void insertUser(User a) throws FileNotFoundException, IOException, ClassNotFoundException {
		User[] old = getallUser();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UserDB"));
		int i = old.length;
		User[] New = new User[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[old.length]=a;
		out.writeObject(New);
		
	}	
	public void insertBill(Bill h) throws FileNotFoundException, IOException, ClassNotFoundException {
		Bill[] old = getallBill();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("BillDB"));
		int i = old.length;
		Bill[] New = new Bill[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[i] = h;
		out.writeObject(New);
	}
	/* Not used
	public void insertRoom(Room h) throws FileNotFoundException, IOException, ClassNotFoundException {
		Room[] old = getallRoom();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("RoomDB"));
		int i = old.length;
		Room[] New = new Room[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[i] = h;
		out.writeObject(New);
		
	}
	*/
	
	/* Not used
	public void insertReview(Review h) throws FileNotFoundException, IOException, ClassNotFoundException {
		Review[] old = getAllReview();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ReviewDB"));
		int i = old.length;
		Review[] New = new Review[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[i] = h;
		out.writeObject(New);
	}
	*/
	
	/* Not used
	public void insertAddress(Address[] afylki) throws FileNotFoundException, IOException, ClassNotFoundException {
		Address[] old = getallAddress();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("AddressDB"));
		int i = old.length;
		Address[] New = new Address[i+afylki.length];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		for(int k = 0; k<afylki.length;k++) {
			New[old.length+k]=afylki[k];
		}
		out.writeObject(New);
		
	}
	*/
	
	/*
	public void insertCreditCard(creditCard h) throws FileNotFoundException, IOException, ClassNotFoundException {
		creditCard[] old = getallcreditCard();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("creditCardDB"));
		int i = old.length;
		creditCard[] New = new creditCard[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[i] = h;
		out.writeObject(New);
		
	}
	*/
	
	/* Not used
	public void insertReservation(Reservation h) throws FileNotFoundException, IOException, ClassNotFoundException {
		Reservation[] old = getallReservation();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ReservationDB"));
		int i = old.length;
		Reservation[] New = new Reservation[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = old[j]; 
		}
		New[i] = h;
		out.writeObject(New);	
	}
	*/
	
	//check duplicates method for all databases. Not really used now but was used while building the project
	public void checkDuplicatesUser() throws FileNotFoundException, ClassNotFoundException, IOException {
		User[] old = getallUser();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UserDB"));
		int nulls = 0;
		
		for(int k = 0;k<old.length;k++) {
			for(int j = k+1;j<old.length;j++) {
				if(old[k].getUserID().equals(old[j].getUserID())&&old[k].getUserID()!="beil") {
					old[j].setUserID("beil");
					nulls++;
				}
			}
		}
		int h = 0;
		User [] New = new User[old.length-nulls];
		for(int r = 0; r<old.length;r++) {
			if(old[r].getUserID()!="beil") {
				New[h] = old[r];
				h++;
			}
		}
			
		
		out.writeObject(New);
	}
	
	
	
	//Filter method which finds the correct hotels according to filters given in the method. Work in progress
	public Hotel[] filterHotels(bFilter bf, intFilter intf) throws FileNotFoundException, ClassNotFoundException, IOException {
		Hotel[] hotels = getallHotel();
		Hotel[] skila = new Hotel[0];
		for(int i = 0; i<hotels.length;i++) {
			boolean[] hotelbf = hotels[i].bFilterVarp();
			int[] hotelintf = hotels[i].intFilterVarp();
			boolean xbf=true;
			boolean xintf=true;
			for(int j=0;j<hotelbf.length;j++) {
				if(hotelbf[j]==false&&bf.bFilterVarp()[j]==true) {
					xbf = false;
				}
			}
			
			if(intf.getStar()>hotels[i].intFilterVarp()[0]) {xintf = false;}
			if(intf.getGrade()>hotels[i].intFilterVarp()[1]) {xintf = false;}
			if(intf.getFromCenterKM()<hotels[i].intFilterVarp()[2]) {xintf = false;}
			if(intf.getBottomPrice()>hotels[i].intFilterVarp()[4]) {xintf = false;}
			if(intf.getTopPrice()<hotels[i].intFilterVarp()[3]) {xintf = false;}
			
			if(xbf==true&&xintf==true) {
				int u = skila.length;
				Hotel[] New = new Hotel[u+1];
				for(int k = 0; k<u;k++) {
					New[k] = skila[k];
				}
				New[u] = hotels[i];
				skila = New;
				
			}
		}
		return skila;
	}
	
	//method that generates random strings
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
	
	//Finds whether the given id exists in the database, if so then it returns the corresponding user object
	public Hotel findHotelbyID(String s) throws FileNotFoundException, ClassNotFoundException, IOException {
		Hotel[] h = getallHotel();
		for(int i = 0; i<h.length;i++) {
			if(s.equals(h[i].getID())) {
				return (Hotel)h[i];
			}
		}
		return null;
	}
	
	//Finds whether the given id exists in the database, if so then it returns the corresponding hotel object
	public User findUserbyID(String s) throws FileNotFoundException, ClassNotFoundException, IOException {
		User[] h = getallUser();
		for(int i = 0; i<h.length;i++) {
			if(s.equals(h[i].getUserID())) {
				return (User)h[i];
			}
		}
		return null;
	}
}
