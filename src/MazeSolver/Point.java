package MazeSolver;

public class Point {
    private int row;
    private int col;
    private Point parent;

    public Point(int y, int x) {
        this.row = y;
        this.col = x;
        this.parent = null;
    }

    public Point(int y, int x, Point parent) {
        this.row = y;
        this.col = x;
        this.parent = parent;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Point getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        return "[" + this.row + ", " + this.col + "]";
    }
}
