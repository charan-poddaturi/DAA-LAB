import java.util.Scanner;

public class HamiltonianCycle {

    static int n;
    static int[][] G;
    static int[] x;

    // Generate next feasible vertex
    static void nextValue(int k) {

        while (true) {

            x[k] = (x[k] + 1) % (n + 1);

            if (x[k] == 0)
                return;

            // Check adjacency
            if (G[x[k - 1]][x[k]] == 1) {

                int j;
                for (j = 1; j < k; j++)
                    if (x[j] == x[k])
                        break;

                // Vertex not repeated
                if (j == k) {

                    // Last vertex must connect to start
                    if (k < n || (k == n && G[x[n]][x[1]] == 1))
                        return;
                }
            }
        }
    }

    // Horowitz-Sahni Hamiltonian Algorithm
    static void hamiltonian(int k) {

        while (true) {

            nextValue(k);

            if (x[k] == 0)
                return;

            if (k == n)
                printSolution();
            else
                hamiltonian(k + 1);
        }
    }

    // Print Hamiltonian cycle
    static void printSolution() {

        System.out.print("Hamiltonian Cycle: ");

        for (int i = 1; i <= n; i++)
            System.out.print(x[i] + " ");

        System.out.println(x[1]);
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

        x[1] = 1;   // Starting vertex fixed

        hamiltonian(2);

        sc.close();
    }
}