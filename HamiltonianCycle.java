import java.util.Scanner;

public class HamiltonianCycle {
    static int n, G[][], x[];

    static boolean isSafe(int k) {
        if (G[x[k - 1]][x[k]] == 0)
            return false;
        for (int i = 1; i < k; i++)
            if (x[i] == x[k])
                return false;
        if (k == n && G[x[n]][x[1]] == 0)
            return false;
        return true;
    }

    static void solve(int k) {
        for (x[k] = 1; x[k] <= n; x[k]++) {
            if (isSafe(k)) {
                if (k == n) {
                    System.out.print("Hamiltonian Cycle: ");
                    for (int i = 1; i <= n; i++)
                        System.out.print(x[i] + " ");
                    System.out.println(x[1]);
                } else
                    solve(k + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();
        G = new int[n + 1][n + 1];
        x = new int[n + 1];
        System.out.println("Enter adjacency matrix:");
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                G[i][j] = sc.nextInt();
        x[1] = 1;
        solve(2);
        sc.close();
    }
}
