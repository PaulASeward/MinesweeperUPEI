public abstract class IndividualSquare {
    public IndividualSquare neighbours[];
    // array neighbours follows this alignment:
    // [NW, N, NE, E, SE, S, SW, W]

    State flaggedState, blankState, selectedState, state;
    int xCoord, yCoord;
    int adjValue;

    public IndividualSquare(int x, int y) {
        xCoord = x;
        yCoord = y;
        adjValue = 0;
        neighbours = new IndividualSquare[8];
        flaggedState = new FlaggedState(this);
        blankState = new BlankState(this);
        selectedState = new SelectedState(this);
        state = blankState;
    }

    void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public State getFlaggedState() {
        return flaggedState;
    }

    public State getBlankState() {
        return blankState;
    }

    public State getSelectedState() {
        return selectedState;
    }

    public char display() {
        return state.display();
    }

    public void flag() {
        state.flag();
    }

    // -1: Mine was selected
    // 0: Border Square
    // 1+: SafeSquare. Total number of safe pushes
    public abstract int push();

    public abstract void adjCalc();
}
