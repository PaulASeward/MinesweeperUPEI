public class BorderSquare extends IndividualSquare{
    public BorderSquare(int x, int y) {
        super(x, y);
        adjValue = 9;
    }

    @Override
    public int push() {
        return 0;
        // Cannot select border square. The adjValue never sums past 8, so this value demonstrates that
    }

    @Override
    public void adjCalc() {
        // Does nothing. Only ever shows #
    }

    @Override
    public char display() {
        return ' '; // Can personalize for border effects
    }

    @Override
    public void flag() {
        // Does nothing. Cannot flag a border
    }
}
