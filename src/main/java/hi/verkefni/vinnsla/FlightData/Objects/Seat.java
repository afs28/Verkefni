package hi.verkefni.vinnsla.FlightData.Objects;
public class Seat{
    private int row;
    private char seatChar;
    public Seat(int row, char seatChar){
        this.row = row;
        this.seatChar = seatChar;
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

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", seatChar=" + seatChar +
                '}';
    }
}