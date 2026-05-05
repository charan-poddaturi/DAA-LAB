import java.util.Scanner;

public class TSP {

    static int N;
    static int[][] a;
    static int min = Integer.MAX_VALUE;
    static int[] best, path;
    static boolean[] visited;

    // Recursive Backtracking function
    static void solve(int city, int level, int cost) {
        // Base case: All cities visited, return to start
        if (level == N) {
            int totalCost = cost + a[city][0];
            if (totalCost < min) {
                min = totalCost;
                System.arraycopy(path, 0, best, 0, N); // Save the best path
                best[N] = 0; // Last step is always returning to 0
            }
            return;
        }

        // Try visiting all other unvisited cities
        for (int i = 0; i < N; i++) {
            // Prune paths that already exceed the minimum cost found
            if (!visited[i] && cost + a[city][i] < min) {
                visited[i] = true;
                path[level] = i;
                
                solve(i, level + 1, cost + a[city][i]); // Recurse
                
                visited[i] = false; // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        N = sc.nextInt();
        a = new int[N][N];
        best = new int[N + 1];
        path = new int[N + 1];
        visited = new boolean[N];

        System.out.println("Enter cost matrix (" + N + "x" + N + "):");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        // Initialize the start city (City 0)
        visited[0] = true;
        path[0] = 0;
        
        solve(0, 1, 0); // Start solving from city 0, level 1, cost 0

        System.out.println("Minimum cost = " + min);
        System.out.print("Path: ");
        for (int i = 0; i <= N; i++) {
            System.out.print(best[i] + " ");
        }

        sc.close();
    }
}
