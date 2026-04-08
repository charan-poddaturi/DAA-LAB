import java.util.*;

public class Job {

    static int[] parent;

    static int find(int s) {
        if (parent[s] == s)
            return s;
        return parent[s] = find(parent[s]); // Path compression
    }

    static void union(int u, int v) {
        parent[v] = u;
    }

    static void jobScheduling(JobNode[] jobs, int n) {

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (JobNode job : jobs)
            maxDeadline = Math.max(maxDeadline, job.deadline);

        parent = new int[maxDeadline + 1];

        for (int i = 0; i <= maxDeadline; i++)
            parent[i] = i;

        int totalProfit = 0;

        for (JobNode job : jobs) {

            int availableSlot = find(job.deadline);

            if (availableSlot > 0) {
                System.out.println("Job " + job.id + " scheduled at slot " + availableSlot);
                totalProfit += job.profit;

                union(find(availableSlot - 1), availableSlot);
            }
        }

        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {

        JobNode[] jobs = {
            new JobNode(1, 2, 100),
            new JobNode(2, 1, 19),
            new JobNode(3, 2, 27),
            new JobNode(4, 1, 25),
            new JobNode(5, 3, 15)
        };

        jobScheduling(jobs, jobs.length);
    }
}

// Separate data class (NOT public)
class JobNode {
    int id, deadline, profit;

    JobNode(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}