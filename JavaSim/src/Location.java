// This class stores the row and column of an entity in the grid.
public class Location {
    private int row;
    private int column;

    // This constructor creates a location from a row and a column.
    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // This method returns the row.
    public int getRow() {
        return row;
    }

    // This method returns the column.
    public int getColumn() {
        return column;
    }
}