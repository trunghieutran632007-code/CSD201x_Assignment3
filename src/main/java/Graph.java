import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * Represents a graph data structure using an adjacency matrix.
 * This class provides functionalities to load graph data from a file,
 * display the weighted matrix, and perform various graph algorithms
 * including Depth-First Search (DFS), Breadth-First Search (BFS),
 * and Dijkstra shortest path algorithm.
 */
public class Graph {
    int[][] a;
    int n;
    String[] cityNames = {"A", "B", "C", "D", "E", "F", "G"};
    int INF = 9999;

    /**
     * Initializes a new Graph with a fixed number of vertices.
     * Sets up the adjacency matrix to store edge weights.
     */
    public Graph() {
        n = 7;
        a = new int[n][n];
    }

    /**
     * Loads the graph's adjacency matrix data from a specified text file.
     *
     * @param filename The name or path of the file containing the matrix data.
     */
    public void loadData(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (sc.hasNextInt()) {
                        a[i][j] = sc.nextInt();
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    /**
     * Displays the weighted adjacency matrix of the graph to the console.
     * Prints "INF" for missing edges or unreachable paths.
     */
    public void displayWeights() {
        System.out.println("The weighted matrix of the graph:");
        System.out.println("================================");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(a[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * Performs a Depth-First Search (DFS) traversal starting from the specified vertex.
     *
     * @param start The index of the starting vertex.
     */
    public void DFS(int start) {
        boolean[] visited = new boolean[n];
        System.out.print("DFS_Graph: ");
        dfsRec(start, visited);
        System.out.println();
    }
    /**
     * A recursive helper method for the Depth-First Search traversal.
     *
     * @param v       The index of the current vertex being visited.
     * @param visited A boolean array tracking which vertices have already been visited.
     */
    private void dfsRec(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(cityNames[v] + " ");
        for (int i = 0; i < n; i++) {
            if (a[v][i] != 0 && a[v][i] != INF && !visited[i]) {
                dfsRec(i, visited);
            }
        }
    }

    /**
     * Finds and prints the shortest path from the start vertex to the end vertex
     * using Dijkstra algorithm.
     *
     * @param start The index of the starting vertex.
     * @param end   The index of the destination vertex.
     */
    public void dijkstra(int start, int end) {
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            path[i] = -1;
            visited[i] = false;
        }

        dist[start] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visited);
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && a[u][v] != 0 && a[u][v] != INF && dist[u] != INF
                        && dist[u] + a[u][v] < dist[v]) {
                    dist[v] = dist[u] + a[u][v];
                    path[v] = u;
                }
            }
        }

        System.out.println("Shortest path from " + cityNames[start] + " to " + cityNames[end] + " is " + dist[end]);
        System.out.print("Path: ");
        printPath(path, end);
        System.out.println();
    }

    /**
     * Finds the unvisited vertex with the minimum distance value.
     * This is a helper method used in Dijkstra algorithm.
     *
     * @param dist    The array storing the shortest distance from the start vertex to each vertex.
     * @param visited The boolean array tracking which vertices have been visited.
     * @return The index of the vertex with the minimum distance, or -1 if all reachable vertices are visited.
     */
    private int minDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    /**
     * Recursively prints the shortest path from the source vertex to the destination vertex.
     * It uses the path array where each element stores its predecessor in the shortest path.
     *
     * @param path The array storing the predecessor of each vertex.
     * @param j    The index of the current destination vertex.
     */
    private void printPath(int[] path, int j) {
        if (path[j] == -1) {
            System.out.print(cityNames[j] + " ");
            return;
        }
        printPath(path, path[j]);
        System.out.print("-> " + cityNames[j] + " ");
    }


    /**
     * Performs a Breadth-First Search traversal of the graph.
     * Visits all vertices level by level starting from the given vertex.
     *
     * @param start The starting vertex index (0 = city A)
     */
    public void BFS(int start) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS_Graph: ");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(cityNames[v] + " ");

            for (int i = 0; i < n; i++) {
                if (a[v][i] != 0 && a[v][i] != INF && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }
}
