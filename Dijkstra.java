import java.util.*;
class Dijkstra {
    int V;
    int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < V - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t\t" + dist[i]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix (" + V + "x" + V + "):");
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph[i][j] = sc.nextInt();
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        Dijkstra d = new Dijkstra();
        d.V = V;
        d.dijkstra(graph, src);
        sc.close();
    }
}
