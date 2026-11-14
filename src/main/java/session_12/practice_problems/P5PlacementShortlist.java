
import java.util.Scanner;

public class P5PlacementShortlist {
    static class Candidate {
        String name;
        int[] scores = new int[6];
        int total;
        int bestGain;
        int startTest;
        int endTest;

        Candidate(String name) {
            this.name = name;
        }

        void calculate() {
            for (int score : scores) {
                total += score;
            }

            int currentSum = scores[1] - scores[0];
            int currentStart = 0;

            bestGain = currentSum;
            startTest = 0;
            endTest = 1;

            for (int i = 1; i < 5; i++) {
                int change = scores[i + 1] - scores[i];

                if (currentSum < 0) {
                    currentSum = change;
                    currentStart = i;
                } else {
                    currentSum += change;
                }

                if (currentSum > bestGain) {
                    bestGain = currentSum;
                    startTest = currentStart;
                    endTest = i + 1;
                }
            }
        }
    }

    static void mergeSort(Candidate[] a, Candidate[] temp,
                          int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(a, temp, left, mid);
        mergeSort(a, temp, mid + 1, right);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (a[i].total >= a[j].total) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (i = left; i <= right; i++) {
            a[i] = temp[i];
        }
    }

    static int cutoffCount(Candidate[] a, int target) {
        int low = 0;
        int high = a.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (a[mid].total >= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    static String findPair(Candidate[] a, int target) {
        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            int sum = a[left].total + a[right].total;

            if (sum == target) {
                return a[left].name + " + " + a[right].name;
            } else if (sum > target) {
                left++;
            } else {
                right--;
            }
        }

        return "no pair";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Candidate[] candidates = new Candidate[n];

        for (int i = 0; i < n; i++) {
            Candidate c = new Candidate(sc.next());

            for (int j = 0; j < 6; j++) {
                c.scores[j] = sc.nextInt();
            }

            c.calculate();
            candidates[i] = c;
        }

        Candidate[] temp = new Candidate[n];
        mergeSort(candidates, temp, 0, n - 1);

        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {
            Candidate c = candidates[i];

            System.out.println((i + 1) + ". " + c.name + " "
                    + c.total + " | best climb "
                    + (c.bestGain >= 0 ? "+" : "")
                    + c.bestGain + " (test "
                    + (c.startTest + 1) + " to "
                    + (c.endTest + 1) + ")");
        }

        while (sc.hasNext()) {
            String query = sc.next();
            int target = sc.nextInt();

            if (query.equalsIgnoreCase("CUTOFF")) {
                System.out.println("CUTOFF " + target + ": "
                        + cutoffCount(candidates, target)
                        + " candidates");
            } else if (query.equalsIgnoreCase("PAIR")) {
                System.out.println("PAIR " + target + ": "
                        + findPair(candidates, target));
            }
        }

        sc.close();
    }
}
