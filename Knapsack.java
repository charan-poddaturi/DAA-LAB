import java.util.Scanner;

public class Knapsack {

    static int knapsack(int[] w, int[] p, int n, int W) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= W; j++)
                dp[i][j] = (w[i-1] <= j)
                    ? Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + p[i-1])
                    : dp[i-1][j];

        return dp[n][W];
    }

    static void traceback(int[] w, int[] p, int n, int W) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= W; j++)
                dp[i][j] = (w[i-1] <= j)
                    ? Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + p[i-1])
                    : dp[i-1][j];

        // Traceback to find selected items
        System.out.print("Items selected (1-indexed): ");
        int j = W;
        for (int i = n; i > 0; i--)
            if (dp[i][j] != dp[i-1][j]) {
                System.out.print(i + " ");
                j -= w[i-1];
            }
        System.out.println();
        System.out.println("Max Profit: " + dp[n][W]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        int[] w = new int[n], p = new int[n];
        System.out.println("Enter weight and profit for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i+1) + " -> Weight: ");
            w[i] = sc.nextInt();
            System.out.print("         Profit: ");
            p[i] = sc.nextInt();
        }

        traceback(w, p, n, W);
        sc.close();
    }
}