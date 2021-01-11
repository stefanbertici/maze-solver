package MazeSolver;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //0 = wall OR already visited
        //1 = available path
        //2 = exit
        int maze[][] = {
                {1,1,1,0,0,1},
                {1,0,1,1,1,1},
                {1,0,2,0,0,1}
        };
        //test maze to check and modify instead of original maze
        int testMaze[][] = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                testMaze[i][j] = maze[i][j];
            }
        }
        //we define our starting point
        Point start = new Point(2, 5);

        depthFirstSearch(testMaze, start);
    }

    public static boolean isValid(int row, int col, int[][] maze) {
        if(row < 0 || row >= maze.length || col < 0 || col >= maze[row].length) {
            return false;
        }

        return true;
    }

    public static void printPath(Stack<Point> path) {
        for (int i = 0; i < path.size(); i++) {
            if(i < path.size() - 1) {
                System.out.print(path.get(i) + " -> ");
            } else {
                System.out.print(path.get(i));
            }
        }
    }

    public static void depthFirstSearch(int[][] maze, Point start) {
        //we create a stack structured path and add our starting point to it
        Stack<Point> path = new Stack<>();
        path.push(start);

        //after we have set our starting point and added it to the path we'll go ahead and mark it with "0"
        //which means that we already visited this point
        maze[start.getRow()][start.getCol()] = 0;

        while(true) {
            //we get the row and col values of the last point added to the path
            int row = path.peek().getRow();
            int col = path.peek().getCol();

            //we try to go left and check for exit
            if(isValid(row, col-1, maze) && maze[row][col-1] != 0) {
                if (maze[row][col-1] == 2) {
                    path.push(new Point(row, col-1));
                    System.out.println("You went left. You won!\n\nThe path to the exit: ");
                    printPath(path);

                    break;
                } else {
                    System.out.println("You went left.");
                    maze[row][col-1] = 0;
                    path.push(new Point(row, col-1));
                    continue;
                }
            }

            //we try to go down and check for exit
            if(isValid(row+1, col, maze) && maze[row+1][col] != 0) {
                if (maze[row+1][col] == 2) {
                    path.push(new Point(row+1, col));
                    System.out.println("You went down. You won!\n\nThe path to the exit: ");
                    printPath(path);

                    break;
                } else {
                    System.out.println("You went down.");
                    maze[row+1][col] = 0;
                    path.push(new Point(row+1, col));
                    continue;
                }
            }

            //we try to go up and check for exit
            if(isValid(row-1, col, maze) && maze[row-1][col] != 0) {
                if (maze[row-1][col] == 2) {
                    path.push(new Point(row-1, col));
                    System.out.println("You went up. You won!\n\nThe path to the exit: ");
                    printPath(path);

                    break;
                } else {
                    System.out.println("You went up.");
                    maze[row-1][col] = 0;
                    path.push(new Point(row-1, col));
                    continue;
                }
            }

            //we try to go right and check for exit
            if(isValid(row, col+1, maze) && maze[row][col+1] != 0) {
                if (maze[row][col+1] == 2) {
                    path.push(new Point(row, col+1));
                    System.out.println("You went right. You won!\n\nThe path to the exit: ");
                    printPath(path);

                    break;
                } else {
                    System.out.println("You went right.");
                    maze[row][col+1] = 0;
                    path.push(new Point(row, col+1));
                    continue;
                }
            }

            //if we cannot go in any direction we backtrack and remove the last added point from our path
            //that way, on the next iteration of the while() loop, we read the previous point's coordinates
            path.pop();
            System.out.println("You went back");

            //if we backtrack all the way and there's still no more available directions to take, well, it's game over
            if(path.size() <= 0) {
                System.out.println("There is no path to the exit");
                break;
            }
        }
    }
}
