public class SafeSquare extends IndividualSquare{
    private static int safePushes = 0;
    public SafeSquare(int x, int y) {
        super(x, y);
        adjValue = 0;
    }

    @Override
    public int push() {
        if (state != selectedState) { // Prevents double counts and infinite back and forth loops
            state.push();
            safePushes++;
            if (adjValue == 0) { // Chain Reaction of Zero Adjacent squares
                for (IndividualSquare neighbour: neighbours){
                    neighbour.push(); // Since all values are already calculated, no mines should explode
                }
            }
        }
        return safePushes; // Returns running total pf pushes
    }

    @Override
    public void adjCalc() { // This always is noe with initialization
        for (IndividualSquare neighbour: neighbours){
            if (neighbour.adjValue == -1) { // has an adjacent mine
                adjValue += 1;
            }
        }
    }
}
