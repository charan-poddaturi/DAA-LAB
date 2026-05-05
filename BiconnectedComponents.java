// biconnected components horowitz
import java.util.*;
public class BiconnectedComponents {

    static int time = 0;
    static int[] dfn, low;
    static boolean[] visited;
    static Stack<Edge> stack = new Stack<>();

    static void dfs(int u, int parent, int[][] adj, int n) {
        visited[u] = true;
        dfn[u] = low[u] = ++time;

        for (int v = 0; v < n; v++) {
            if (adj[u][v] == 1) {

                // Tree edge
                if (!visited[v]) {
                    stack.push(new Edge(u, v));
                    dfs(v, u, adj, n);

                    low[u] = Math.min(low[u], low[v]);

                    // Biconnected component found
                    if (low[v] >= dfn[u]) {
                        System.out.print("Biconnected Component: ");
                        Edge e;
                        do {
                            e = stack.pop();
                            System.out.print("(" + e.u + "," + e.v + ") ");
                        } while (!(e.u == u && e.v == v));
                        System.out.println();
                    }
                }

                // Back edge
                else if (v != parent && dfn[v] < dfn[u]) {
                    low[u] = Math.min(low[u], dfn[v]);
                    stack.push(new Edge(u, v));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] adj = new int[n][n];
        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adj[i][j] = sc.nextInt();

        dfn = new int[n];
        low = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, -1, adj, n);

                // Remaining edges form a BCC
                if (!stack.isEmpty()) {
                    System.out.print("Biconnected Component: ");
                    while (!stack.isEmpty()) {
                        Edge e = stack.pop();
                        System.out.print("(" + e.u + "," + e.v + ") ");
                    }
                    System.out.println();
                }
            }
        }
        sc.close();
    }
}
class Edge {
    int u, v;
    Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}
