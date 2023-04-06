import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 10;
        float percentOfMineSquares = 10;
        RegularBoard board = new RegularBoard(size, percentOfMineSquares);
        board.printBoard();

        System.out.println("Actions are either flag: putting a flag on coordinate or push: selecting a coordinate");
        while (board.gameOn && board.correctPushes < board.numberOfSafeMines) {
            System.out.println("Enter a command (x##/y##/action), or type 'quit':");
            String command = scanner.nextLine();
            if (command.equals("quit")) {
                break;
            }
            board.enterCommand(command);
            board.printBoard();
        }
        if (!board.gameOn) {
            System.out.println("Game over, you lost.");
        } else if (board.correctPushes == board.numberOfSafeMines) {
            System.out.println("Congratulations, you won!");
        }
    }
}