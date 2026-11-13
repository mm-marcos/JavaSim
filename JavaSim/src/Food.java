// This class represents food. Food stays in its cell and does not take actions.
public class Food extends Entity {

    // This constructor creates food at the given location.
    public Food(Location location) {
        super(location);
    }

    // This method returns F so the grid can display food.
    @Override
    public char getSymbol() {
        return 'F';
    }

    // This method does nothing because food does not move or act.
    @Override
    public void takeTurn(World world) {
        // Food stays where it is.
    }
}