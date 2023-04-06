public class MineSquare extends IndividualSquare{
    public MineSquare(int x, int y) {
        super(x, y);
        adjValue = -1;
    }

    @Override
    public int push() {
        state.push();
        System.out.println("BOOOMMM!! Sorry you have lost");
        return adjValue; // Game is Lost
    }

    @Override
    public void adjCalc() {
        // No calculations needed for MineSquares
    }
}
