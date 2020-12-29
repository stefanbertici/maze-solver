package MazeSolver;

public class Point {
    private int row;
    private int col;

    public Point(int y, int x) {
        this.row = y;
        this.col = x;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    @Override
    public String toString() {
        return "[" + this.row + ", " + this.col + "]";
    }
}
