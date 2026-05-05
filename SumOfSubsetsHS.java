import java.util.Scanner;

public class SumOfSubsets {

    static int n, M;
    static int[] w, x;

    static void printSubset() {
        System.out.print("Subset: ");
        for (int i = 1; i <= n; i++)
            if (x[i] == 1)
                System.out.print(w[i] + " ");
        System.out.println();
    }

    static void solve(int s, int k, int r) {
        x[k] = 1;
        if (s + w[k] == M)
            printSubset();
        else if (k + 1 <= n && s + w[k] + w[k + 1] <= M)
            solve(s + w[k], k + 1, r - w[k]);

        if (k + 1 <= n && s + r - w[k] >= M && s + w[k + 1] <= M) {
            x[k] = 0;
            solve(s, k + 1, r - w[k]);
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

        solve(0, 1, total);
    }
}
