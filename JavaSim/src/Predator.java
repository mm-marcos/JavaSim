
public class Predator extends Entity {
    
	public Predator(Location location) {
        super(location);
    }
	
    @Override
    public char getSymbol() {
        return 'P';
    }
    
    @Override 
    public void takeTurn(World world){
        Location creatureLocation = world.findNearestCreature(getLocation());
        
        if (creatureLocation != null) {
            int nextRow = getLocation().getRow();
            int nextColumn = getLocation().getColumn();

            // Move vertically first. If already in the same row, move horizontally.
            if (creatureLocation.getRow() < nextRow) {
                nextRow = nextRow - 1;
            } else if (creatureLocation.getRow() > nextRow) {
                nextRow = nextRow + 1;
            } else if (creatureLocation.getColumn() < nextColumn) {
                nextColumn = nextColumn - 1;
            } else if (creatureLocation.getColumn() > nextColumn) {
                nextColumn = nextColumn + 1;
            }

            Location nextLocation = new Location(nextRow, nextColumn);
            world.moveEntity(this, nextLocation);
        }

    }

}
