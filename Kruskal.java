import java.util.*;

public class Job {

    static int[] parent;

    static int find(int i) {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }

    static void union(int u, int v) {
        parent[v] = u;
    }

    static void jobScheduling(int[] id, int[] deadline, int[] profit, int n) {

        // Sort jobs by profit descending (bubble sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (profit[j] < profit[j + 1]) {
                    int tmp;
                    tmp = profit[j];   profit[j] = profit[j+1];   profit[j+1] = tmp;
                    tmp = deadline[j]; deadline[j] = deadline[j+1]; deadline[j+1] = tmp;
                    tmp = id[j];       id[j] = id[j+1];             id[j+1] = tmp;
                }
            }
        }

        // Find max deadline
        int maxDeadline = 0;
        for (int i = 0; i < n; i++)
            if (deadline[i] > maxDeadline)
                maxDeadline = deadline[i];

        // Initialize parent array
        parent = new int[maxDeadline + 1];
        for (int i = 0; i <= maxDeadline; i++)
            parent[i] = i;

        int totalProfit = 0;

        System.out.println("Scheduled Jobs:");
        for (int i = 0; i < n; i++) {
            int availableSlot = find(deadline[i]);
            if (availableSlot > 0) {
                System.out.println("Job " + id[i] + " scheduled at slot " + availableSlot);
                totalProfit += profit[i];
                union(find(availableSlot - 1), availableSlot);
            }
        }

        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        int[] id       = new int[n];
        int[] deadline = new int[n];
        int[] profit   = new int[n];

        System.out.println("Enter id, deadline, profit for each job:");
        for (int i = 0; i < n; i++) {
            System.out.print("Job " + (i + 1) + ": ");
            id[i]       = sc.nextInt();
            deadline[i] = sc.nextInt();
            profit[i]   = sc.nextInt();
        }

        jobScheduling(id, deadline, profit, n);
        sc.close();
    }
}
