public class SquareCreator implements Creator{
    @Override
    public IndividualSquare setSquare(int x, int y, char c) {
        if (c == 'm') { //MineSquare
            return new MineSquare(x, y);
        } else //SafeSquare
            return new SafeSquare(x, y);
    }
}
