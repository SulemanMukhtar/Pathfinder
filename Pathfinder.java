import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;


public class Pathfinder {

    private int[][] grid;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Pathfinder(int size) {
        // Initialize grid
        this.grid = new int[size][size];
        // Randomly initialize start and end points
        Random rand = new Random();
        this.startX = rand.nextInt(size);
        this.startY = rand.nextInt(size);
        this.endX = rand.nextInt(size);
        this.endY = rand.nextInt(size);
    }

    public void findPath() {
        // Determine which algorithm to use based on start and end points
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        if (dx + dy < 15) {
            // Use A* algorithm for small distances
            useAStar();
        } else if (dx > 2 * dy) {
            // Use BFS for long, narrow paths
            useBFS();
        } else if (dy > 2 * dx) {
            // Use BFS for long, tall paths
            useBFS();
        } else {
            // Use A* algorithm for other cases
            useAStar();
        }
    }

    private void useDijkstra() {
        // Initialize distances and visited array
        int size = grid.length;
        int[][] dist = new int[size][size];
        boolean[][] visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        // Set starting distance to zero
        dist[startX][startY] = 0;

        // Run Dijkstra's algorithm
        for (int i = 0; i < size * size; i++) {
            // Find the unvisited node with the smallest distance
            int minDist = Integer.MAX_VALUE;
            int minNodeX = -1;
            int minNodeY = -1;
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (!visited[x][y] && dist[x][y] < minDist) {
                        minDist = dist[x][y];
                        minNodeX = x;
                        minNodeY = y;
                    }
                }
            }
            // Mark the node as visited
            visited[minNodeX][minNodeY] = true;

            // Update distances to neighboring nodes
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, 1, 0, -1};
            for (int j = 0; j < 4; j++) {
                int neighborX = minNodeX + dx[j];
                int neighborY = minNodeY + dy[j];
                if (neighborX >= 0 && neighborX < size && neighborY >= 0 && neighborY < size
                        && !visited[neighborX][neighborY] && grid[neighborX][neighborY] != 1) {
                    int altDist = dist[minNodeX][minNodeY] + 1; // assume edge weight of 1
                    if (altDist < dist[neighborX][neighborY]) {
                        dist[neighborX][neighborY] = altDist;
                    }
                }
            }
            // Stop when the end node is visited
            if (visited[endX][endY]) {
                break;
            }
        }

        // Print the shortest distance to the end node
        System.out.println("Shortest distance to end node: " + dist[endX][endY]);
    }

    private void useAStar() {
        int[][] dist = new int[grid.length][grid[0].length];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[startX][startY] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{startX, startY, 0});

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0], y = curr[1];
            String pos = x + "," + y;
            if (visited.contains(pos)) {
                continue;
            }
            visited.add(pos);
            if (x == endX && y == endY) {
                break;
            }
            int cost = dist[x][y];
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] != 1) {
                    int ncost = cost + 1;
                    if (ncost < dist[nx][ny]) {
                        dist[nx][ny] = ncost;
                        int h = Math.abs(nx - endX) + Math.abs(ny - endY);
                        pq.offer(new int[]{nx, ny, ncost + h});
                    }
                }
            }
        }

        // Print the shortest distance from start to end
        System.out.println("Shortest distance from start to end: " + dist[endX][endY]);
    }

    private void useBFS() {
        int[][] dist = new int[grid.length][grid[0].length];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[startX][startY] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            int cost = dist[x][y];
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] != 1) {
                    int ncost = cost + 1;
                    if (ncost < dist[nx][ny]) {
                        dist[nx][ny] = ncost;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        // Print the shortest distance from start to end
        System.out.println("Shortest distance from start to end: " + dist[endX][endY]);
    }

    public static void main(String[] args) {
        Pathfinder pathfinder = new Pathfinder(10000);
        System.out.println("Start point: (" + pathfinder.startX + ", " + pathfinder.startY + ")");
        System.out.println("End point: (" + pathfinder.endX + ", " + pathfinder.endY + ")");
        pathfinder.findPath();
    }
}
