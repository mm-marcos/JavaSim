import java.util.Random;

// This factory creates random locations and makes random respawn decisions.
public class RandomFactory {
    private static Random random = new Random();

    // This method returns a random empty location, or null if the grid is full.
    public static Location randomEmptyLocation(Entity[][] grid) {
        int emptyCount = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == null) {
                    emptyCount = emptyCount + 1;
                }
            }
        }

        if (emptyCount == 0) {
            return null;
        }

        int chosenEmptyCell = random.nextInt(emptyCount);
        int currentEmptyCell = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == null) {
                    if (currentEmptyCell == chosenEmptyCell) {
                        return new Location(row, column);
                    }
                    currentEmptyCell = currentEmptyCell + 1;
                }
            }
        }

        return null;
    }

    // This method returns true when a random percentage check succeeds.
    public static boolean shouldRespawn(int percent) {
        if (percent <= 0) {
            return false;
        }

        if (percent >= 100) {
            return true;
        }

        int number = random.nextInt(100);

        if (number < percent) {
            return true;
        }

        return false;
    }
}