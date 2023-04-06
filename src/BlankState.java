public class BlankState extends State{
    public BlankState(IndividualSquare individualSquare) {
        super(individualSquare);
    }

    @Override
    public void push() {
        this.individualSquare.setState(individualSquare.getSelectedState());
    }

    @Override
    public void flag() {
        this.individualSquare.setState(individualSquare.getFlaggedState());
    }

    @Override
    public char display() {
        return '-';
    }
}
