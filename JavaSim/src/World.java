import java.util.ArrayList;

// This class owns the grid and manages all simulation rules and entities.
public class World {
    private Entity[][] grid;
    private ArrayList<Entity> entities;

    // This constructor creates the grid and places the starting creatures and food.
    public World() {
        grid = new Entity[Settings.GRID_HEIGHT][Settings.GRID_WIDTH];
        entities = new ArrayList<Entity>();

        for (int i = 0; i < Settings.STARTING_CREATURES; i++) {
            Location location = RandomFactory.randomEmptyLocation(grid);
            if (location == null) {
                break;
            }
            addEntity(new Creature(location));
        }

        for (int i = 0; i < Settings.STARTING_FOOD; i++) {
            Location location = RandomFactory.randomEmptyLocation(grid);
            if (location == null) {
                break;
            }
            addEntity(new Food(location));
        }
    }

    // This method adds an entity when its location is inside the grid and empty.
    public void addEntity(Entity entity) {
        if (entity == null) {
            return;
        }

        int row = entity.getLocation().getRow();
        int column = entity.getLocation().getColumn();

        if (row < 0 || row >= grid.length) {
            return;
        }

        if (column < 0 || column >= grid[row].length) {
            return;
        }

        if (grid[row][column] != null) {
            return;
        }

        grid[row][column] = entity;
        entities.add(entity);
    }

    // This method removes an entity from the grid and the entity list.
    public void removeEntity(Entity entity) {
        if (entity == null) {
            return;
        }

        entity.setAlive(false);

        int row = entity.getLocation().getRow();
        int column = entity.getLocation().getColumn();

        if (row >= 0 && row < grid.length && column >= 0 && column < grid[row].length) {
            if (grid[row][column] == entity) {
                grid[row][column] = null;
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) == entity) {
                entities.remove(i);
                break;
            }
        }
    }

    // This method finds the food with the shortest up-down-left-right distance.
    public Location findNearestFood(Location start) {
        if (start == null) {
            return null;
        }

        Location nearestFood = null;
        int shortestDistance = 2147483647;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Entity entity = grid[row][column];

                if (entity != null && entity.isAlive()) {
                    if (entity instanceof Food) {
                        int rowDistance = row - start.getRow();
                        int columnDistance = column - start.getColumn();

                        if (rowDistance < 0) {
                            rowDistance = -rowDistance;
                        }

                        if (columnDistance < 0) {
                            columnDistance = -columnDistance;
                        }

                        int distance = rowDistance + columnDistance;

                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                            nearestFood = new Location(row, column);
                        }
                    }
                }
            }
        }

        return nearestFood;
    }

    // This method moves a creature one cell and returns true if it eats food.
    public boolean moveCreature(Creature creature, Location nextLocation) {
        if (creature == null || nextLocation == null) {
            return false;
        }

        int nextRow = nextLocation.getRow();
        int nextColumn = nextLocation.getColumn();

        if (nextRow < 0 || nextRow >= grid.length) {
            return false;
        }

        if (nextColumn < 0 || nextColumn >= grid[nextRow].length) {
            return false;
        }

        Entity occupant = grid[nextRow][nextColumn];
        boolean ateFood = false;

        if (occupant != null) {
            if (occupant instanceof Food) {
                removeEntity(occupant);
                ateFood = true;
            } else {
                return false;
            }
        }

        Location oldLocation = creature.getLocation();
        grid[oldLocation.getRow()][oldLocation.getColumn()] = null;

        creature.setLocation(nextLocation);
        grid[nextRow][nextColumn] = creature;

        return ateFood;
    }

    // This method runs one turn for each entity, then gives empty cells a chance to grow food.
    public void takeTurn() {
        ArrayList<Entity> turnOrder = new ArrayList<Entity>();

        // Make a list of the entities that should act during this turn.
        for (int i = 0; i < entities.size(); i++) {
            turnOrder.add(entities.get(i));
        }

        for (int i = 0; i < turnOrder.size(); i++) {
            Entity entity = turnOrder.get(i);

            if (entity != null && entity.isAlive()) {
                entity.takeTurn(this);
            }
        }

        respawnFood();
    }

    // This method checks each empty cell for a chance to receive new food.
    public void respawnFood() {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == null) {
                    if (RandomFactory.shouldRespawn(Settings.FOOD_RESPAWN_PERCENT)) {
                        addEntity(new Food(new Location(row, column)));
                    }
                }
            }
        }
    }

    // This method returns the grid so the view can draw its current contents.
    public Entity[][] getGrid() {
        return grid;
    }

    // This method returns the grid height.
    public int getHeight() {
        return grid.length;
    }

    // This method returns the grid width.
    public int getWidth() {
        return grid[0].length;
    }

    // This method returns a text version of the grid for the model driver.
    public String getGridText() {
        String result = "";

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == null) {
                    result = result + ". ";
                } else {
                    result = result + grid[row][column].getSymbol() + " ";
                }
            }
            result = result + "\n";
        }

        return result;
    }
}