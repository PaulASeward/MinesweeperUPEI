public class BorderCreator implements Creator{
    @Override
    public IndividualSquare setSquare(int x, int y, char c) {
        return new BorderSquare(x, y);
    }
}
