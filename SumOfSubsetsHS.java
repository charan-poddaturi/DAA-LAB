// sum of subsets week 9 
import java.util.Scanner;

public class SumOfSubsetsHS {

    static int n, M;
    static int[] w;     // weights
    static int[] x;     // solution vector

    // Print solution
    static void printSubset() {
        System.out.print("Subset: ");
        for (int i = 1; i <= n; i++) {
            if (x[i] == 1)
                System.out.print(w[i] + " ");
        }
        System.out.println();
    }

    // Horowitz Sahani Backtracking Method
    static void sumOfSubsets(int s, int k, int r) {

        x[k] = 1;   // include wk

        if (s + w[k] == M)
            printSubset();

        else if (k + 1 <= n && s + w[k] + w[k + 1] <= M)
            sumOfSubsets(s + w[k], k + 1, r - w[k]);

        // exclude wk
        if (k + 1 <= n && (s + r - w[k] >= M) && (s + w[k + 1] <= M)) {
            x[k] = 0;
            sumOfSubsets(s, k + 1, r - w[k]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        n = sc.nextInt();

        w = new int[n + 1];
        x = new int[n + 1];

        System.out.println("Enter elements in increasing order:");

        int total = 0;
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            total += w[i];
        }

        System.out.print("Enter target sum: ");
        M = sc.nextInt();

        sumOfSubsets(0, 1, total);
    }
}