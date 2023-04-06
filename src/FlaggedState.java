public class FlaggedState extends State{

    public FlaggedState(IndividualSquare individualSquare) {
        super(individualSquare);
    }

    @Override
    public void push() {
        this.individualSquare.setState(individualSquare.getSelectedState());
    }

    @Override
    public void flag() {
        this.individualSquare.setState(individualSquare.getBlankState());
    }

    @Override
    public char display() {
        return 'F';
    }
}
