// This abstract class is the parent of every kind of entity in the world.
public abstract class Entity {
    private Location location;
    private boolean alive;

    // This constructor gives an entity a location and marks it as alive.
    public Entity(Location location) {
        this.location = location;
        this.alive = true;
    }

    // This method returns the entity's current location.
    public Location getLocation() {
        return location;
    }

    // This method changes the entity's location.
    public void setLocation(Location location) {
        this.location = location;
    }

    // This method tells the world whether the entity is still alive.
    public boolean isAlive() {
        return alive;
    }

    // This method marks the entity as no longer alive.
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Child classes provide the character used to display this entity.
    public abstract char getSymbol();

    // Child classes provide their behavior for one simulation turn.
    public abstract void takeTurn(World world);
}