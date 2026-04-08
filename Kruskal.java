import java.util.*;

class Kruskal {

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

    static void kruskalMST(Edge[] edges, int V) {
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
        int V = 4;

        Edge[] edges = new Edge[5];

        edges[0] = new Edge(); edges[0].u = 0; edges[0].v = 1; edges[0].weight = 10;
        edges[1] = new Edge(); edges[1].u = 0; edges[1].v = 2; edges[1].weight = 6;
        edges[2] = new Edge(); edges[2].u = 0; edges[2].v = 3; edges[2].weight = 5;
        edges[3] = new Edge(); edges[3].u = 1; edges[3].v = 3; edges[3].weight = 15;
        edges[4] = new Edge(); edges[4].u = 2; edges[4].v = 3; edges[4].weight = 4;

        kruskalMST(edges, V);
    }
}