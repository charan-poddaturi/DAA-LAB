import java.util.*;

class Graph {
    int V;
    List<Integer>[] adj;
    int time = 0;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    void dfs(int u, boolean[] visited, int[] disc, int[] low, int parent,
             boolean[] ap, Stack<int[]> st) {

        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj[u]) {

            if (!visited[v]) {
                children++;
                st.push(new int[]{u, v});

                dfs(v, visited, disc, low, u, ap, st);

                low[u] = Math.min(low[u], low[v]);

                // articulation point condition
                if ((parent == -1 && children > 1) ||
                    (parent != -1 && low[v] >= disc[u])) {

                    ap[u] = true;

                    // print biconnected component
                    System.out.print("Biconnected Component: ");
                    while (true) {
                        int[] edge = st.pop();
                        System.out.print("(" + edge[0] + "-" + edge[1] + ") ");
                        if (edge[0] == u && edge[1] == v) break;
                    }
                    System.out.println();
                }

            } else if (v != parent && disc[v] < disc[u]) {
                low[u] = Math.min(low[u], disc[v]);
                st.push(new int[]{u, v});
            }
        }
    }

    void findAPandBCC() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] ap = new boolean[V];
        Stack<int[]> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, disc, low, -1, ap, st);

                // remaining edges in stack
                if (!st.isEmpty()) {
                    System.out.print("Biconnected Component: ");
                    while (!st.isEmpty()) {
                        int[] e = st.pop();
                        System.out.print("(" + e[0] + "-" + e[1] + ") ");
                    }
                    System.out.println();
                }
            }
        }

        // print articulation points
        System.out.print("Articulation Points: ");
        for (int i = 0; i < V; i++) {
            if (ap[i]) System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        g.findAPandBCC();
    }
}