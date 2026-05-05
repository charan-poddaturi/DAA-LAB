import java.util.Scanner;

public class Knapsack {

    static int[][] dp;

    static void fillTable(int[] w, int[] p, int n, int W) {
        dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                // Can we include item i?
                if (w[i-1] <= j)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - w[i-1]] + p[i-1]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
    }

    static void traceback(int[] w, int[] p, int n, int W) {
        fillTable(w, p, n, W);

        // Trace back to find which items were selected
        System.out.print("Items selected (1-indexed): ");
        int j = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][j] != dp[i-1][j]) {
                System.out.print(i + " ");
                j -= w[i-1];
            }
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

        int[] w = new int[n];
        int[] p = new int[n];

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
