
public class Hunter extends Entity {
	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	
	public Hunter(Location location) {
        super(location);
    }
	
    @Override
    public char getSymbol() {
        return 'H';
    }
    
    @Override 
    public void takeTurn(World world){
        if (!isAlive()) {
            return;
        }
        
        Location predatorLocation = world.findNearestPredator(getLocation());
        
        if (predatorLocation != null) {
            int nextRow = getLocation().getRow();
            int nextColumn = getLocation().getColumn();

            // Move vertically first. If already in the same row, move horizontally.
            if (predatorLocation.getRow() < nextRow) {
                nextRow = nextRow - 1;
            } else if (predatorLocation.getRow() > nextRow) {
                nextRow = nextRow + 1;
            } else if (predatorLocation.getColumn() < nextColumn) {
                nextColumn = nextColumn - 1;
            } else if (predatorLocation.getColumn() > nextColumn) {
                nextColumn = nextColumn + 1;
            }

            Location nextLocation = new Location(nextRow, nextColumn);
            world.moveEntity(this, nextLocation);
        }

    }

}