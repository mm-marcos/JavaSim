// This class represents a creature that searches for food and can starve.
public class Creature extends Entity {
    private int turnsWithoutFood;

    // This constructor creates a creature at the given location.
    public Creature(Location location) {
        super(location);
        turnsWithoutFood = 0;
    }

    // This method returns C so the grid can display a creature.
    @Override
    public char getSymbol() {
        return 'C';
    }

    // This method moves the creature toward its nearest food and checks for starvation.
    @Override
    public void takeTurn(World world) {
        if (!isAlive()) {
            return;
        }

        boolean ateFood = false;
        Location foodLocation = world.findNearestFood(getLocation());

        if (foodLocation != null) {
            int nextRow = getLocation().getRow();
            int nextColumn = getLocation().getColumn();

            // Move vertically first. If already in the same row, move horizontally.
            if (foodLocation.getRow() < nextRow) {
                nextRow = nextRow - 1;
            } else if (foodLocation.getRow() > nextRow) {
                nextRow = nextRow + 1;
            } else if (foodLocation.getColumn() < nextColumn) {
                nextColumn = nextColumn - 1;
            } else if (foodLocation.getColumn() > nextColumn) {
                nextColumn = nextColumn + 1;
            }

            Location nextLocation = new Location(nextRow, nextColumn);
            ateFood = world.moveEntity(this, nextLocation);
        }

        if (ateFood) {
            turnsWithoutFood = 0;
        } else {
            turnsWithoutFood = turnsWithoutFood + 1;
        }

        if (turnsWithoutFood >= Settings.STARVATION_TURNS) {
            world.removeEntity(this);
        }
    }
}