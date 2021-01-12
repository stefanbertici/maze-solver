package MazeSolver;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //0 = wall OR already visited
        //1 = available path
        //2 = exit
        int[][] maze = {
                {1,1,1,2,1,1},
                {1,0,1,0,0,1},
                {1,1,1,1,0,1},
                {1,1,1,1,1,1},
                {1,0,1,0,1,1},
                {1,1,1,1,0,1}
        };

        //we define our starting point
        Point startPoint = new Point(0, 5);

        depthFirstSearch(maze, startPoint);
        System.out.println("\n");
        breathFirstSearch(maze, startPoint);
    }

    //test maze to check and modify instead of original maze
    public static int[][] initializeTestMaze(int[][] maze) {
        int[][] testMaze = new int[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            System.arraycopy(maze[i], 0, testMaze[i], 0, maze[0].length);
        }

        return testMaze;
    }

    public static boolean isValid(int row, int col, int[][] maze) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[row].length;
    }

    public static void printPath(Stack<Point> path, boolean isDFS) {
        if (isDFS) {
            System.out.println("You won!\nThe path to the exit using DFS (depth first search) is:");
        } else {
            System.out.println("You won!\nThe path to the exit using BFS (breath first search) is:");
        }

        for (int i = 0; i < path.size(); i++) {
            if (i < path.size() - 1) {
                System.out.print(path.get(i) + " -> ");
            } else {
                System.out.print(path.get(i));
            }
        }
    }

    public static void depthFirstSearch(int[][] givenMaze, Point startPoint) {
        int[][] maze = initializeTestMaze(givenMaze);
        Stack<Point> path = new Stack<>();
        path.push(startPoint);

        //after we have set our starting point and added it to the path we'll go ahead and mark it with "0"
        //which means that we already visited this point
        maze[startPoint.getRow()][startPoint.getCol()] = 0;

        while (true) {
            //we get the row and col values of the last point added to the path
            int row = path.peek().getRow();
            int col = path.peek().getCol();

            //we try to go right and check for exit
            if (isValid(row, col+1, maze) && maze[row][col+1] != 0) {
                if (maze[row][col+1] == 2) {
                    path.push(new Point(row, col+1));
                    printPath(path, true);
                    break;
                } else {
                    maze[row][col+1] = 0;
                    path.push(new Point(row, col+1));
                    continue;
                }
            }

            //we try to go down and check for exit
            if (isValid(row+1, col, maze) && maze[row+1][col] != 0) {
                if (maze[row+1][col] == 2) {
                    path.push(new Point(row+1, col));
                    printPath(path, true);
                    break;
                } else {
                    maze[row+1][col] = 0;
                    path.push(new Point(row+1, col));
                    continue;
                }
            }

            //we try to go left and check for exit
            if (isValid(row, col-1, maze) && maze[row][col-1] != 0) {
                if (maze[row][col-1] == 2) {
                    path.push(new Point(row, col-1));
                    printPath(path, true);
                    break;
                } else {
                    maze[row][col-1] = 0;
                    path.push(new Point(row, col-1));
                    continue;
                }
            }

            //we try to go up and check for exit
            if (isValid(row-1, col, maze) && maze[row-1][col] != 0) {
                if (maze[row-1][col] == 2) {
                    path.push(new Point(row-1, col));
                    printPath(path, true);
                    break;
                } else {
                    maze[row-1][col] = 0;
                    path.push(new Point(row-1, col));
                    continue;
                }
            }

            //if we cannot go in any direction we backtrack and remove the last added point from our path
            //that way, on the next iteration of the while() loop, we read the previous point's coordinates
            path.pop();

            //if we backtrack all the way and there's still no more available directions to take, well, it's game over
            if (path.size() <= 0) {
                System.out.println("There is no path to the exit :(");
                break;
            }
        }
    }

    public static void breathFirstSearch(int[][] givenMaze, Point startPoint) {
        int[][] maze = initializeTestMaze(givenMaze);
        Point endPoint = null;
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);

        //after we have set our starting point and added it to the queue we'll go ahead and mark it with "0"
        //which means that we already visited this point
        maze[startPoint.getRow()][startPoint.getCol()] = 0;

        while (!queue.isEmpty()) {
            //we get the row and col values of the first point in queue
            Point point = queue.remove();
            int row = point.getRow();
            int col = point.getCol();

            //we try to go into every direction and check for exit, if found, save it as end point
            //if not found add new point to queue as child of previous point
            //right
            if (isValid(row, col+1, maze) && maze[row][col+1] != 0) {
                if (maze[row][col+1] == 2) {
                    endPoint = new Point(row, col+1, point);
                    break;
                } else {
                    maze[row][col+1] = 0;
                    queue.add(new Point(row, col+1, point));
                }
            }

            //down
            if (isValid(row+1, col, maze) && maze[row+1][col] != 0) {
                if (maze[row+1][col] == 2) {
                    endPoint = new Point(row+1, col, point);
                    break;
                } else {
                    maze[row+1][col] = 0;
                    queue.add(new Point(row+1, col, point));
                }
            }

            //left
            if (isValid(row, col-1, maze) && maze[row][col-1] != 0) {
                if (maze[row][col-1] == 2) {
                    endPoint = new Point(row, col-1, point);
                    break;
                } else {
                    maze[row][col-1] = 0;
                    queue.add(new Point(row, col-1, point));
                }
            }

            //up
            if (isValid(row-1, col, maze) && maze[row-1][col] != 0) {
                if (maze[row-1][col] == 2) {
                    endPoint = new Point(row-1, col, point);
                    break;
                } else {
                    maze[row-1][col] = 0;
                    queue.add(new Point(row-1, col, point));
                }
            }
        }

        if (queue.isEmpty()) {
            System.out.println("there is no path to the exit :(");
        } else {
            //we reconstruct the correct path starting from the endpoint going backwards with using
            //recursion and the getParent method
            Stack<Point> path = new Stack<>();
            addPointsInReverseOrder(path, endPoint);
            printPath(path, false);
        }
    }

    public static void addPointsInReverseOrder(Stack<Point> path, Point endPoint) {
        if (endPoint == null) {
            System.out.println("Error: endPoint is null!");
            return;
        }

        if (endPoint.getParent() != null) {
            addPointsInReverseOrder(path, endPoint.getParent());
        }

        path.push(endPoint);
    }
}
