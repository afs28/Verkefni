package hi.verkefni.vinnsla.HotelData;

import java.io.Serializable;
import java.sql.Date;

//Basic class that holds creditcard information. Needed for User.

public class creditCard implements Serializable {
	
	private String cardNum;
	private String expirationDate;
	
	
	public creditCard(String cardNum, String string) {
		this.setCardNum(cardNum);
		this.setExpirationDate(string);
	}
	//Getterar og setterar
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String string) {
		this.expirationDate = string;
	}
	/////////////////////////////////
	
	
}
