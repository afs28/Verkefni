package hi.verkefni.vinnsla.HotelData.Objects;

import java.io.Serializable;
import java.util.Random;

// class that holds information about the submitted reviews from users.

public class Review implements Serializable {
	
	private String reviewNum; //identification string to distinguish between reviews
	private String text; // text that the user submitted for the review
	
	//This was not implemented because the admin feature has not been implemented
	//private boolean isPrivate; is the review private (only readable by admins?) 
	
	//This was not implemented because there was not enough time
	//private boolean spam;
	
	private int grade; //grade that the user gave the hotel
	private User writer; //user that wrote the review
	
	//Constructor
	public Review(String text, int grade, User writer) {
		this.reviewNum = getRandomString();
		this.setText(text);
		
		//Not used/tested yet
		//this.setPrivate(isPrivate);
		//this.setSpam(false);
		
		this.setGrade(grade);
		this.setWriter(writer);
	}
	
	//Getters and setters
	
	public String getRnum() {
		return this.reviewNum;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {

		this.grade = grade;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	
	//Not implemented yet/not tested
	/*
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public boolean isSpam() {
		return spam;
	}
	public void setSpam(boolean spam) {
		this.spam = spam;
	}
	*/

	
	////////////////////////////////////
	
	//Not implemented because spam is not yet an implemented feature.
		/*
		public void setSpam(Review r, boolean n) {
			r.spam = n;
		}
		*/
	
	//Random String generator of length 8
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

        System.out.println(a + " = reviewNum");

        return a;
    }
}
