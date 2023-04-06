public abstract class State {
    char value;
    DisplayStrategy strategy;
    IndividualSquare individualSquare;

    public State(IndividualSquare individualSquare) {
        this.individualSquare = individualSquare;
        this.strategy = new RegularStrategy();
    }

    public abstract void push();

    public abstract void flag();

    public abstract char display();
}
