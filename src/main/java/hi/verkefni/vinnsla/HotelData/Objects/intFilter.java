package hi.verkefni.vinnsla.HotelData.Objects;

import java.io.Serializable;

// class that holds int values. Needed for Hotel class. 

public class intFilter implements Serializable {
	
	private int star;
	private int grade;
	private int fromCenterKM;
	private int bottomPrice;
	private int topPrice;
	
	public intFilter(int center) {
		this.setStar(0);
		this.setFromCenterKM(center);
		this.setGrade(0);
		this.setBottomPrice(0);
		this.setTopPrice(0);
	}
	//for search
	public intFilter(int star,int center,int grade,int bp,int tp) {
		this.setStar(0);
		this.setFromCenterKM(center);
		this.setGrade(0);
		this.setBottomPrice(bp);
		this.setTopPrice(tp);
	}
	//Getterar og setterar
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getFromCenterKM() {
		return fromCenterKM;
	}

	public void setFromCenterKM(int fromCenterKM) {
		this.fromCenterKM = fromCenterKM;
	}

	public int getBottomPrice() {
		return bottomPrice;
	}

	public void setBottomPrice(int bottomPrice) {
		this.bottomPrice = bottomPrice;
	}

	public int getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(int topPrice) {
		this.topPrice = topPrice;
	}
	/////////////////////////////////
	
	public int[] intFilterVarp() {
		int [] meistari = new int[5];
		meistari[0]=this.getStar();
		meistari[1]=this.getGrade();
		meistari[2]=this.getFromCenterKM();
		meistari[3]=this.getBottomPrice();
		meistari[4]=this.getTopPrice();
		return meistari;
	}
}
