// n queens problems week 10
import java.util.Scanner;

public class NQueensHS {

    static int x[];   // solution vector
    static int n;

    // Place function (feasibility test)
    static boolean place(int k, int i) {
        for (int j = 1; j < k; j++) {
            if (x[j] == i || Math.abs(x[j] - i) == Math.abs(j - k))
                return false;
        }
        return true;
    }

    // Horowitz Sahani Backtracking
    static void nQueens(int k) {

        for (int i = 1; i <= n; i++) {

            if (place(k, i)) {
                x[k] = i;

                if (k == n)
                    printSolution();
                else
                    nQueens(k + 1);
            }
        }
    }

    // Print solution
    static void printSolution() {
        System.out.println("\nSolution:");

        for (int i = 1; i <= n; i++)
            System.out.print(x[i] + " ");

        System.out.println("\nBoard:");

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i] == j)
                    System.out.print(" Q ");
                else
                    System.out.print(" . ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of queens: ");
        n = sc.nextInt();

        x = new int[n + 1]; // index starts from 1

        nQueens(1);
    }
}