package hi.verkefni.vinnsla.HotelData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;


//User class to hold information about the users.


public class User implements Serializable {

	private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String userID;
    private creditCard creditcard;
    private Reservation reservationHistory[]; //history of reservations made by this user
    private Review userReview[]; //history of reviews made by this user
	private Hotel[] favorites; //holds around all the hotels that the user decided to favorite
	
	
	//Constructor
	 public User(String firstName, String lastName, String email,Address add,creditCard creditcard) {
	        this.setFirstName(firstName);
	        this.setLastName(lastName);
	        this.setEmail(email);
	        this.setAddress(add);
	        this.setUserID(getRandomString());
	        this.setCreditcard(creditcard);
	        this.reservationHistory = new Reservation[0];
	        this.favorites= new Hotel[0];
	        this.userReview = new Review[0];
	    }


	//Getters and setters
	 
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Reservation[] getReservationHistory() {
		return reservationHistory;
	}
	public void setReservationHistory(Reservation reservationHistory[]) {
		this.reservationHistory = reservationHistory;
	}
	
	public Hotel[] getFavorites() {
		return favorites;
	}
	public void setFavorites(Hotel[] favorites) {
		this.favorites = favorites;
	}
	
	public Review[] getUserReview() {
		return userReview;
	}
	public void setUserReview(Review userReview[]) {
		this.userReview = userReview;
	}
	public creditCard getCreditcard() {
		return creditcard;
	}
	public void setCreditcard(creditCard creditcard) {
		this.creditcard = creditcard;
	}
	//////////////////////////////////////////////
	
	//Method that returns the full name of the user
	public String getFullName() {
        return firstName +" "+lastName; 
    }
	
	//add/remove favorites
	
    public void addFavorites(Hotel h) throws FileNotFoundException, ClassNotFoundException, IOException {
        int i = this.favorites.length;
        Hotel[] New = new Hotel[i+1];
        for(int j = 0; j < i; j++) {
            New[j] = this.favorites[j]; 
        }
        New[i] = h;
        this.setFavorites(New);
        DatabaseController DC = new DatabaseController();
		DC.replaceUser(this);
    }
    public void removeFavorites(Hotel h) throws FileNotFoundException, ClassNotFoundException, IOException {
        int i = this.favorites.length;
        Hotel[] New = new Hotel[i-1];
        int store = -1;

        for(int j = 0; j < i; j++) {
            if(h == this.favorites[j]) {
                store=j;
            }
        }
        for(int k = 0; k<i-1; k++) {
            if(k != store) {
                New[k] = this.favorites[k];
            }
        }
        this.setFavorites(New);
        DatabaseController DC = new DatabaseController();
		DC.replaceUser(this);
    }
    
    ///////////////////////
    
    
    //add/remove user review
    public void removeUserReview(Review r) throws FileNotFoundException, ClassNotFoundException, IOException {
    	int i = this.userReview.length;
		Review[] New = new Review[i-1];
		int store = -1;
		
		for(int j = 0; j < i; j++) {
			if(r == this.userReview[j]) {
				store=j;
			}
		}
		for(int k = 0; k<i; k++) {
			if(k != store) {
				New[k] = this.userReview[k];
			}
		}
		this.setUserReview(New);
		DatabaseController DC = new DatabaseController();
		DC.replaceUser(this);
    }
    public void addUserReview(Review r) throws FileNotFoundException, ClassNotFoundException, IOException {
    	int i = this.userReview.length;
		Review[] New = new Review[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = this.userReview[j]; 
		}
		New[i] = r;
		this.setUserReview(New);
		DatabaseController DC = new DatabaseController();
		DC.replaceUser(this);
    }
	/////////////////////////
    
    //add/remove resHistory
    public void addResHistory(Reservation r) throws FileNotFoundException, ClassNotFoundException, IOException {
    	int i = this.reservationHistory.length;
		Reservation[] New = new Reservation[i+1];
		for(int j = 0; j < i; j++) {
			New[j] = this.reservationHistory[j]; 
		}
		New[i] = r;
		this.setReservationHistory(New);
		DatabaseController DC = new DatabaseController();
		DC.replaceUser(this);
    }
    
    /* Code not used but can be helpful in the future
    public void removeResHistory(Reservation r) {
    	int i = this.reservationHistory.length;
		Reservation[] New = new Reservation[i-1];
		int store = -1;
		
		for(int j = 0; j < i; j++) {
			if(r == this.reservationHistory[j]) {
				store=j;
			}
		}
		for(int k = 0; k<i; k++) {
			if(k != store) {
				New[k] = this.reservationHistory[k];
			}
		}
		this.setReservationHistory(New);
    }
    */
	////////////////////////////

	
	//String generator for UserID
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

        System.out.println(a + " = userID");

        return a;
    }

}
