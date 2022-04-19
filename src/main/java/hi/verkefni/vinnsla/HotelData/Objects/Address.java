package hi.verkefni.vinnsla.HotelData.Objects;

import java.io.Serializable;


//Basic class that holds address information. Needed for User.

public class Address implements Serializable{
	
	private String City;
	private String Street;
	private String aptNum;
	private int postNum;
	
	public Address(String city, String street, String aptNum, int postNum ) {
		this.setCity(city);
		this.setStreet(street);
		this.setAptNum(aptNum);
		this.setPostNum(postNum);
	}
	//Getterar og setterar
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getAptNum() {
		return aptNum;
	}
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	//////////////////////
	
	
}
