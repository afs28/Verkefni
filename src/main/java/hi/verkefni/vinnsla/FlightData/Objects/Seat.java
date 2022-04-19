package hi.verkefni.vinnsla.FlightData.Objects;
public class Seat{
    private int row;
    private char seatChar;
    public Seat(int r, char sc){
        this.row = r;
        this.seatChar = sc;
    }

    public int seatCharToInt(){
        switch(this.seatChar){
            case 'a': 
                return 0;
            case 'b': 
                return 1;
            case 'c': 
                return 2;
            case 'd':
                return 3;
        }
        return -1;
    }

    public int getRow(){
        return this.row;
    }

    public int getSeatChar(){
        return this.seatChar;
    }
}