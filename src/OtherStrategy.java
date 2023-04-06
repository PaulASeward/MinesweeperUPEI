public class OtherStrategy implements DisplayStrategy{
    @Override
    public char display(char c) {
        return Character.toUpperCase(c);
    }
}
