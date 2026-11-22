// This class keeps all simulation settings together so they are easy to change.
public class Settings {

    // Number of columns in the grid.
    public static int GRID_WIDTH = 20;

    // Number of rows in the grid.
    public static int GRID_HEIGHT = 12;

    // Number of creatures placed in the world when it starts.
    public static int STARTING_CREATURES = 5;

    // Number of food items placed in the world when it starts.
    public static int STARTING_FOOD = 18;

    // Number of predators placed in the world when it starts.
    public static int STARTING_PREDATORS = 2;
    
    // Number of turns a creature can go without eating before it starves.
    public static int STARVATION_TURNS = 8;

    // Chance, as a whole-number percentage, that each empty cell gets food each turn.
    public static int FOOD_RESPAWN_PERCENT = 5;

    // Number of milliseconds between simulation turns.
    public static int TURN_DELAY_MS = 400;
}