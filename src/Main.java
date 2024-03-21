import java.util.*;

public class Main {
    public static int numRows = 10;
    public static int numCols = 10;
    public static int amountOfShips = 2;
    public static String[][] oceanPl = new String[numRows][numCols];
    public static String[][] oceanCp = new String[numRows][numCols];

    public static void main(String[] args) {
        // Step 1 - Create ocean map
        System.out.println("*** Welcome to Battle Ship game ***");
        System.out.print(" sea is empty");
        printMap(oceanPl);
        // Step 2 - Deploy player´s ships
        deployPlShips();
        // Step 3 - Deploy computer´s ships
        deployCpShips();
        // Step 4 - Battle
        Battle();
        // Step 5 - Game over
        gameOver();
    }

    public static void printMap(String[][] ocean) {
        System.out.println("\n  0 1 2 3 4 5 6 7 8 9 ");
        String[] columnIndex = new String[10];
        char[] charArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
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

    public static void deployPlShips() {
        Random rand = new Random();
        int[] length = {2, 3};
        int randNumRows;
        int randNumCols;
        System.out.println("Player deploy ships... ");
        for (int i = 0; i <= amountOfShips - 1; i++) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter row number");
            int rowNum = input.nextInt();
            //charArray = input.nextLine();
            System.out.println("Enter col number");
            int colNum = input.nextInt();
            input.nextLine();
            System.out.println("Enter orientation: h for horizontal or v for vertical");
            String orientation = input.nextLine();

            if (orientation.equals("h")) { // Horizontal
                for (int j = 0; j < length[i]; j++) {
                    if ((colNum + j) < 10) {
                        oceanPl[rowNum][colNum + j] = "X ";
                    } else {
                        System.out.println("Ship is outside the board, try again");
                        break;
                    }
                }
                System.out.println("Player board ");
                printMap(oceanPl);
            } else {
                for (int j = 0; j < length[i]; j++) {
                    if ((rowNum + j) < 10) {
                        oceanPl[rowNum + j][colNum] = "X ";
                    } else {
                        System.out.println("Ship is outside the board, try again");
                        break;
                    }
                }
                System.out.println("Player board ");
                printMap(oceanPl);
            }


            /*
            Random random = new Random();
            //orientation = random.nextBoolean();
            System.out.println("Value is:" + orientation);
            //Collections.shuffle(Arrays.asList(ocean[randNumRowsPlayer][randNumColsPlayer]));
            oceanPl[rowNum][colNum] = "X ";
            for (int j = 1; j< length[i]; j++){
                oceanPl[rowNum][colNum+j] = "X ";
            }*/
        }
    }

    public static void deployCpShips() {
        Random rand = new Random();
        int[] length = {2, 3};
        int randNumRows;
        int randNumCols;
        System.out.print("Computer deploy ships... ");
        for (int i = 0; i < amountOfShips; i++) {
            randNumRows = rand.nextInt(numRows - length[i] - 1 + 1);
            randNumCols = rand.nextInt(numCols - length[i] - 1 + 1);
            Ship first = new Ship(randNumRows, randNumCols, 2);
            Random random = new Random();
            boolean orientation = random.nextBoolean();
            oceanCp[randNumRows][randNumCols] = "X ";
            for (int j = 1; j < length[i]; j++) {
                if (orientation == false) {
                    oceanCp[randNumRows][randNumCols + j] = "X ";
                } else {
                    oceanCp[randNumRows + j][randNumCols] = "X ";
                }
            }
        }
        System.out.println("Computer board ");
        printMap(oceanCp);
    }

    public static void Battle() {
        while (checkForPlShips() == true && checkForCpShips() == true) {
            if (checkForPlShips() == true) {
                playerShoot();
            }
            if (checkForCpShips() == true) {
                computerShoot();
            }
        }
        System.out.println(" --- Game over --- ");
    }
        /*for (int row = 0; row <= numRows-1; row++){
            for (int col = 0; col<= numCols-1; col++){
                if (oceanCp[row][col] == "X ") {
                    computerShoot();
                }
            }
        }*/
    public static void playerShoot() {
        int rowValue;
        int colValue;
        System.out.println(" Player turn ");
        Scanner input = new Scanner(System.in);
        System.out.println("Row: ");
        rowValue = input.nextInt();

        System.out.println("Column: ");
        colValue = input.nextInt();

        if (oceanCp[rowValue][colValue] == "X ") {
            System.out.println("Hit");
            oceanCp[rowValue][colValue] = "1 ";
            printMap(oceanCp);
        } else if (oceanCp[rowValue][colValue] == null) {
            System.out.println("Miss");
            oceanCp[rowValue][colValue] = "0 ";
            printMap(oceanCp);
        } else {
            System.out.println("Already been shoot");
            printMap(oceanCp);
        }
    }

    public static void computerShoot() {
        int rowValue;
        int colValue;
        System.out.println(" Computer turn ");
        rowValue = new Random().nextInt(numRows);
        colValue = new Random().nextInt(numCols);

        if (oceanPl[rowValue][colValue] == "X ") {
            System.out.println("Hit");
            oceanPl[rowValue][colValue] = "1c";
            printMap(oceanCp);
        } else if (oceanPl[rowValue][colValue] == null) {
            System.out.println("Miss");
            oceanPl[rowValue][colValue] = "0c";
            printMap(oceanPl);
        } else {
            System.out.println("Already been shoot");
            printMap(oceanPl);
        }

    }

    public static boolean checkForPlShips() {
        for (int row = 0; row <= numRows - 1; row++) {
            for (int col = 0; col <= numCols - 1; col++) {
                if (oceanPl[row][col] == "X ") {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkForCpShips() {
        for (int row = 0; row <= numRows - 1; row++) {
            for (int col = 0; col <= numCols - 1; col++) {
                if (oceanCp[row][col] == "X ") {
                    return true;
                }
            }
        }
        return false;
    }

    public static void gameOver(){
        if (checkForPlShips() == true && checkForCpShips() == false){
            System.out.println("* Player won the battle *");
        }
        else if (checkForPlShips() == false && checkForCpShips() == true) {
            System.out.println("* Computer won the battle *");
        }
    }
}