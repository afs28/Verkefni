package main.java.hi.verkefni.vidmot;

public class User {
    //variables
    private String userName;
    private String password;
    private String emailAddress;
    private String socialSecurityNumber;
    private String paymentCard;
    private String address;

    //constructor
    public User(String userName, String password, String emailAddress, String socialSecurityNumber, String paymentCard, String address) {
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.socialSecurityNumber = socialSecurityNumber;
        this.paymentCard = paymentCard;
        this.address = address;
    }

    //getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getPaymentCard() {
        return paymentCard;
    }

    public String getAddress() {
        return address;
    }
}
