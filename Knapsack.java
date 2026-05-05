import java.util.*;
class Kruskal {
    int V;
    static class Edge implements Comparable<Edge> {
        int u, v, weight;
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }
    static void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }
    void kruskalMST(int[][] graph) {
        // Extract edges from adjacency matrix (upper triangle only, avoid duplicates)
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < V; i++)
            for (int j = i + 1; j < V; j++)
                if (graph[i][j] != 0) {
                    Edge e = new Edge();
                    e.u = i; e.v = j; e.weight = graph[i][j];
                    edgeList.add(e);
                }
        Edge[] edges = edgeList.toArray(new Edge[0]);
        Arrays.sort(edges);
        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;
        System.out.println("Edge \tWeight");
        for (Edge edge : edges) {
            int x = find(parent, edge.u);
            int y = find(parent, edge.v);
            if (x != y) {
                System.out.println(edge.u + " - " + edge.v + "\t" + edge.weight);
                union(parent, x, y);
            }
        }
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
        Kruskal k = new Kruskal();
        k.V = V;
        k.kruskalMST(graph);
        sc.close();
    }
}
