package hi.verkefni.vinnsla.HotelData.Objects;

import java.io.Serializable;

//Filter with boolean values. Needed for hotel class.

public class bFilter implements Serializable{
	
	private boolean gym;
	private boolean pets;
	private boolean pool;
	private boolean restaurant;
	private boolean terrace;
	private boolean breakfast;
	private boolean wifi;
	private boolean parking;
	private boolean smoking;
	
	public bFilter(boolean gym, boolean pets, boolean pool, boolean restaurant,
			boolean terrace, boolean breakfast, boolean wifi, boolean parking, boolean smoking) {
		this.setGym(gym);
		this.setPets(pets);
		this.setPool(pool);
		this.setRestaurant(restaurant);
		this.setTerrace(terrace);
		this.setBreakfast(breakfast);
		this.setWifi(wifi);
		this.setParking(parking);
		this.setSmoking(smoking);
	}
	//Getterar og setterar
	public boolean isGym() {
		return gym;
	}
	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public boolean isPets() {
		return pets;
	}

	public void setPets(boolean pets) {
		this.pets = pets;
	}

	public boolean isPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public boolean isRestaurant() {
		return restaurant;
	}

	public void setRestaurant(boolean restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isTerrace() {
		return terrace;
	}

	public void setTerrace(boolean terrace) {
		this.terrace = terrace;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}
	/////////////////////////////////////
	
	public boolean[] bFilterVarp() {
		boolean [] meistari = new boolean[9];
		meistari[0]=this.isGym();
		meistari[1]=this.isPets();
		meistari[2]=this.isPool();
		meistari[3]=this.isRestaurant();
		meistari[4]=this.isTerrace();
		meistari[5]=this.isBreakfast();
		meistari[6]=this.isWifi();
		meistari[7]=this.isParking();
		meistari[8]=this.isSmoking();
		return meistari;
	}
	
}
