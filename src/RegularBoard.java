import java.util.Random;
import java.util.Scanner;

public class RegularBoard {
    public IndividualSquare grid[][];
    int correctPushes, size, numberOfSafeMines, numberOfTurns;

    boolean gameOn;
    float percentOfMineSquares;
    Creator squareCreator, borderCreator;

    public RegularBoard(int size, float percent) {
        this.size = size;
        this.percentOfMineSquares = percent;
        gameOn = true;
        numberOfSafeMines = 0;
        correctPushes = 0;
        numberOfTurns = 0;

        grid = new IndividualSquare[size+2][size+2];
        squareCreator = new SquareCreator();
        borderCreator = new BorderCreator();

        // Initiale board creating each square with the corresponding factory.
        initializeBoard();

        // Call on board to calculate adjacency counts of nearby mines
        populateNeighbours();
    }

    private void initializeBoard() {
        for (int i=0; i<size+2; i++) {
            for (int j=0; j<size+2; j++) {
                if (i==0 || j==0 || i==size+1 ||j==size+1) {
                    grid[i][j]=borderCreator.setSquare(i,j,'b');
                }
                else {
                    Random rand = new Random();
                    int randomNumber = rand.nextInt(101); // Generate a random number between 0 and 100
                    if (randomNumber < percentOfMineSquares) {
                        grid[i][j] = squareCreator.setSquare(i,j,'m');// Create MineSquare
                    } else {
                        grid[i][j] = squareCreator.setSquare(i,j,'s');   // create SafeSquare
                        numberOfSafeMines++;
                    }
                }
            }
        }
    }

    private void populateNeighbours() {
        for (int i=1; i<=size; i++) {
            for (int j=1; j<=size; j++) {
                for (int k=-1; k<=1; k++) {
                    for (int l=-1; l<=1; l++) {
                        if (k==0 && l==0) {}//do nothing as this is the center
                        else if (k==1 && l==1) { // prevent index out of bound
                            grid[i][j].neighbours[4] = grid[i+k][j+l];
                        } else {
                            grid[i][j].neighbours[3*k + l + 4] = grid[i+k][j+l];
                        }
                    }
                }
                // Now this current cell has neighbours populated, it can calculate adjacent mines
                grid[i][j].adjCalc();
            }
        }
    }

    public void printBoard() { // Display each individual square. Appearance depends on state of square
        System.out.println();
        System.out.print("   ");
        for (int j = 0; j < size + 1; j++) {
            System.out.print(String.format("%2d ", j));
        }
        System.out.println("    Turns: " + numberOfTurns);
        for (int i=0; i<size+1; i++) {
            for (int j=0; j<size+1; j++) {
                if (j==0) {System.out.print(String.format("%2d ", i));}
                System.out.print(" " + grid[i][j].display() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Facade method for easy interface for client
    public void enterCommand(String command) {
        String[] fields = command.split("/"); // Split command by '/'
        if (fields.length != 3) {
            System.out.println("Invalid command: " + command);
            return;
        }

        int y = parseCoordinate(fields[0]);
        int x = parseCoordinate(fields[1]);

        // Call the appropriate method based on third field
        String action = fields[2].toLowerCase();
        if (action.equals("push")) {
            push(x, y);
        } else if (action.equals("flag")) {
            flag(x, y);
        } else {
            System.out.println("Invalid action: " + action);
        }
    }

    // Helper Methods
    private int parseCoordinate(String field) {
        int coordinate = Integer.parseInt(field.replaceAll("\\D", ""));
        if (coordinate > size) {
            coordinate = size - 1;
        } else if (coordinate <= 0) {
            coordinate = 1;
        }
        return coordinate;
    }

    private void push(int x, int y) {
        numberOfTurns++;
        int safePushTotal = grid[x][y].push();

        if (safePushTotal == -1) { // Stepped on a mine
            Scanner scanner = new Scanner(System.in);
            System.out.println("You have activated a mine. Type r to retry last move, or any key to quit.");
            String answer = scanner.nextLine();
            if (!answer.equals("r")){
                gameOn = false;  // Game Over. User lost
            }
            else { // retry last move
                grid[x][y].setState(grid[x][y].getFlaggedState());
            }
        } else if (safePushTotal == 0) {
            System.out.print("You cannot select the border");
        } else correctPushes = safePushTotal; // Track running total to see if user won
    }

    private void flag(int x, int y) {
        grid[x][y].flag();
    }


}
