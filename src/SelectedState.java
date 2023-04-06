public class SelectedState extends State{

    public SelectedState(IndividualSquare individualSquare) {
        super(individualSquare);
    }

    @Override
    public void push() {
        // Does nothing. Can't push a selected button
    }

    @Override
    public void flag() {
        // Empty Implementation
    }

    @Override
    public char display() {
        if (this.individualSquare.adjValue < 0) {return 'b';} //MineSquare
        else if (this.individualSquare.adjValue == 0) {return ' ';} //Empty Safe Square
        else return (char) (this.individualSquare.adjValue + '0');} // Convert int to char
        // return (char) this.individualSquare.adjValue;} // SafeSquare

}
