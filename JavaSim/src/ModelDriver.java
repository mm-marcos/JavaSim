// This class tests the model without opening a window.
public class ModelDriver {

    // This main method creates a world, runs 20 turns, and prints each grid.
    public static void main(String[] args) {
        World world = new World();

        for (int turn = 1; turn <= 20; turn++) {
            world.takeTurn();

            System.out.println("Turn " + turn);
            System.out.println(world.getGridText());
        }
    }
}