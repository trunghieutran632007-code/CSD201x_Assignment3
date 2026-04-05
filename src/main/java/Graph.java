import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {
    int[][] a;
    int n;
    String[] cityNames = {"A", "B", "C", "D", "E", "F", "G"};
    int INF = 9999;

    public Graph() {
        n = 7;
        a = new int[n][n];
    }

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

    // 7. DFS
    public void DFS(int start) {
        boolean[] visited = new boolean[n];
        System.out.print("DFS_Graph: ");
        dfsRec(start, visited);
        System.out.println();
    }

    private void dfsRec(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(cityNames[v] + " ");
        for (int i = 0; i < n; i++) {
            if (a[v][i] != 0 && a[v][i] != INF && !visited[i]) {
                dfsRec(i, visited);
            }
        }
    }

    // 8. Dijkstra
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
