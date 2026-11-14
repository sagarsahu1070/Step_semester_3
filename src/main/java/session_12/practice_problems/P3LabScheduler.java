
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class P3LabScheduler {
    static class Job {
        String name;
        int remaining;

        Job(String name, int remaining) {
            this.name = name;
            this.remaining = remaining;
        }
    }

    static class FinishedJob {
        String name;
        int finishTime;

        FinishedJob(String name, int finishTime) {
            this.name = name;
            this.finishTime = finishTime;
        }
    }

    static class LabScheduler {
        private final int quantum;
        private final Queue<Job> queue = new ArrayDeque<>();
        private final List<FinishedJob> finished = new ArrayList<>();
        private final StringBuilder timeline = new StringBuilder();
        private int currentTime = 0;

        LabScheduler(int quantum) {
            this.quantum = quantum;
        }

        void addJob(String name, int time) {
            queue.offer(new Job(name, time));
        }

        void run() {
            while (!queue.isEmpty()) {
                Job job = queue.poll();

                int start = currentTime;
                int runTime = Math.min(quantum, job.remaining);

                currentTime += runTime;
                job.remaining -= runTime;

                if (timeline.length() > 0) {
                    timeline.append(" ");
                }

                timeline.append(job.name)
                        .append("(").append(start)
                        .append("-").append(currentTime).append(")");

                if (job.remaining > 0) {
                    queue.offer(job);
                } else {
                    finished.add(new FinishedJob(
                            job.name, currentTime));
                }
            }
        }

        void printResults() {
            System.out.println(timeline);
            System.out.print("finish");

            for (FinishedJob job : finished) {
                System.out.print(" " + job.name + " " + job.finishTime);
                if (job != finished.get(finished.size() - 1)) {
                    System.out.print(",");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String first = sc.next();
        int quantum;

        if (first.equalsIgnoreCase("Quantum")) {
            quantum = sc.nextInt();
        } else {
            quantum = Integer.parseInt(first);
        }

        LabScheduler scheduler = new LabScheduler(quantum);

        while (sc.hasNext()) {
            String name = sc.next();
            int time = sc.nextInt();
            scheduler.addJob(name, time);
        }

        scheduler.run();
        scheduler.printResults();
        sc.close();
    }
}
