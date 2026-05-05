import java.util.Scanner;

public class MatrixChain {

    static int n;
    static int[] p;
    static long[][] m;
    static int[][] s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of matrices (>=2): ");
        n = sc.nextInt();

        p = new int[n + 1];
        m = new long[n + 1][n + 1];
        s = new int[n + 1][n + 1];

        System.out.println("Enter " + (n + 1) + " dimensions:");
        for (int i = 0; i <= n; i++) {
            System.out.print("P" + i + ": ");
            p[i] = sc.nextInt();
        }

        computeCost();

        System.out.print("Optimal Multiplication Order: ");
        printOrder(1, n);
        System.out.println();
        System.out.println("Minimum Multiplications = " + m[1][n]);

        sc.close();
    }

    static void computeCost() {
        for (int i = 1; i <= n; i++)
            m[i][i] = 0;

        for (int i = n; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                m[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    long cost = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    static void printOrder(int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
            return;
        }
        System.out.print("(");
        printOrder(i, s[i][j]);
        printOrder(s[i][j] + 1, j);
        System.out.print(")");
    }
}
