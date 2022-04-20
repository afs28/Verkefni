package hi.verkefni.vinnsla.FlightData.Objects;

public class Customer{
    private final String name;
    private int phone;
    private String email;
    private String address;
    private int postalCode;
    private String nation;

    public Customer(String name, int phone, String email, String address, int postalCode, String nation) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.nation = nation;
    }

    public String getName(){
        return this.name;
    }

    public int getPhone(){
        return this.phone;
    }

    public String getAddress(){
        return this.address;
    }

    public String getEmail(){
        return this.email;
    }

    public int getPostalCode(){
        return this.postalCode;
    }

    public String getNation(){
        return this.nation;
    }


    public void setPhone(int phone){
        this.phone = phone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    public void setNation(String nation){
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", nation='" + nation + '\'' +
                '}';
    }
}