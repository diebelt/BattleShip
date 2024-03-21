import java.util.ArrayList;

public class Ship {
    int rowPosition;
    int colPosition;
    int length;

    ArrayList<Ship> ships = new ArrayList<Ship>();

    //constructor
    public Ship(int rowPosition, int colPosition, int length){
      this.rowPosition = rowPosition;
      this.colPosition = colPosition;
      this.length = length;
      this.ships = new ArrayList<Ship>();

    }
}
