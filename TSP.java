import java.util.*;

public class TSP {

    static int N;
    static int[][] a;
    static int min = Integer.MAX_VALUE;
    static int[] best;

    static void tsp() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        // Each entry: [cost, level, city, path encoded as visited mask]
        // We'll use Node arrays: [cost, level, city] + path[] + visited[]
        // Keep Node class but simpler
        Node root = new Node();
        root.level = 1;
        root.city  = 0;
        root.cost  = 0;
        root.path  = new int[N + 1];
        root.visited = new boolean[N];
        root.path[0] = 0;
        root.visited[0] = true;

        Queue<Node> queue = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        queue.add(root);
        best = new int[N + 1];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // All cities visited → return to start
            if (cur.level == N) {
                int total = cur.cost + a[cur.city][0];
                if (total < min) {
                    min  = total;
                    best = cur.path.clone();
                    best[N] = 0;
                }
                continue;
            }

            // Try visiting each unvisited city
            for (int i = 0; i < N; i++) {
                if (!cur.visited[i]) {
                    Node child    = new Node();
                    child.level   = cur.level + 1;
                    child.city    = i;
                    child.cost    = cur.cost + a[cur.city][i];
                    child.path    = cur.path.clone();
                    child.visited = cur.visited.clone();
                    child.path[cur.level] = i;
                    child.visited[i]      = true;

                    if (child.cost < min)
                        queue.add(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        N = sc.nextInt();
        a = new int[N][N];

        System.out.println("Enter cost matrix (" + N + "x" + N + "):");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = sc.nextInt();

        tsp();

        System.out.println("Minimum cost = " + min);
        System.out.print("Path: ");
        for (int i = 0; i <= N; i++)
            System.out.print(best[i] + " ");

        sc.close();
    }
}

class Node {
    int level, city, cost;
    int[] path;
    boolean[] visited;
}
