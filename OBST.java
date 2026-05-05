import java.util.Scanner;

public class OBST {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] p = new int[n + 1];
        int[] q = new int[n + 1];
        int[][] w = new int[n + 1][n + 1];
        int[][] c = new int[n + 1][n + 1];
        int[][] r = new int[n + 1][n + 1];

        System.out.println("Enter the Probabilities of Successful searches:");
        for (int i = 1; i <= n; i++) {
            System.out.print("p" + i + ": ");
            p[i] = sc.nextInt();
        }

        System.out.println("Enter the Probabilities of Unsuccessful Searches:");
        for (int i = 0; i <= n; i++) {
            System.out.print("q" + i + ": ");
            q[i] = sc.nextInt();
        }

        // Initialize diagonal
        System.out.println("\nW\t\tC\t\tR");
        for (int i = 0; i <= n; i++) {
            w[i][i] = q[i];
            System.out.printf("W[%d][%d]: %d\tC[%d][%d]: %d\tR[%d][%d]: %d\n",
                i, i, w[i][i], i, i, c[i][i], i, i, r[i][i]);
        }

        // Fill tables
        System.out.println();
        for (int m = 1; m <= n; m++) {
            for (int i = 0; i <= n - m; i++) {
                int j = i + m;

                w[i][j] = p[j] + q[j] + w[i][j - 1];

                int min = Integer.MAX_VALUE;
                for (int k = i + 1; k <= j; k++) {
                    int cost = c[i][k - 1] + c[k][j] + w[i][j];
                    if (cost < min) {
                        min = cost;
                        r[i][j] = k;
                    }
                }
                c[i][j] = min;

                System.out.printf("W[%d][%d]: %d\tC[%d][%d]: %d\tR[%d][%d]: %d\n",
                    i, j, w[i][j], i, j, c[i][j], i, j, r[i][j]);
            }
            System.out.println();
        }

        System.out.println("Minimum cost = " + c[0][n]);
        System.out.println("Root = " + r[0][n]);

        sc.close();
    }
}
