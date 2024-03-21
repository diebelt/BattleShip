public class Ocean {
   int numRows = 10;
   int numCols = 10;
   String [][] ocean;
   Ship [] ships;

   public Ocean (){
       this.ocean = new String [numRows][numCols];
   }

    public void printMap() {
        System.out.println("\n  0 1 2 3 4 5 6 7 8 9 ");
        String[] columnIndex = new String[10];
        char[] charArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int row = 0; row < charArray.length; row++) { //Create the numbers
            System.out.print(charArray[row] + "|");
            for (int col = 0; col < ocean[row].length; col++) {
                if (ocean[row][col] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(ocean[row][col]);
                }
            }
            System.out.println("|" + charArray[row]);
        }
        System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
    }

    public void deployShip(int rowNum, int colNum, String orientation){
        int[] length = {1, 2};
        if (orientation.equals("h")) { // Horizontal
            for (int j = 0; j < length[0]; j++) {
                if ((colNum + j) < 10) {
                    ocean[rowNum][colNum + j] = "X ";
                    System.out.println(rowNum + " " + colNum);
                } else {
                    System.out.println("Ship is outside the board, try again");
                    break;
                }
            }
            System.out.println("Player board ");
            this.printMap();
        } else {
            for (int j = 0; j < length[0]; j++) {
                if ((rowNum + j) < 10) {
                    ocean[rowNum + j][colNum] = "X ";
                } else {
                    System.out.println("Ship is outside the board, try again");
                    break;
                }
            }
            System.out.println("Player board ");
            this.printMap();
        }
    }
}
