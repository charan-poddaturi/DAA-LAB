import java.util.Scanner;

public class Kruskal {

    static int[] parent;

    static int find(int i) {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }
    
    static void union(int i, int j) {
        parent[find(j)] = find(i);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];

        System.out.println("Enter cost adjacency matrix (use 9999 for no edge):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        // Each node is its own parent initially
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        int mincost = 0;
        int edgesAdded = 0;

        System.out.println("\nEdges in MST:");

        // Keep picking minimum cost edge until n-1 edges added
        while (edgesAdded < n - 1) {

            // Find minimum cost edge that doesn't form a cycle
            int min = 9999;
            int u = 0, v = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (find(i) != find(j) && cost[i][j] < min) {
                        min = cost[i][j];
                        u = i;
                        v = j;
                    }
                }
            }

            // Add edge to MST
            union(u, v);
            System.out.println(u + " - " + v + " : " + cost[u][v]);
            mincost += cost[u][v];
            edgesAdded++;
        }

        System.out.println("Minimum Cost = " + mincost);
        sc.close();
    }
}
