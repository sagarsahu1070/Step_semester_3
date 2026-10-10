import java.util.*;

public class A3NetBalancePeriods {

    static long countPeriods(int[] transactions, int k) {
        Map<Long, Integer> frequency = new HashMap<>();
        long sum = 0, count = 0;

        frequency.put(0L, 1);

        for (int value : transactions) {
            sum += value;

            count += frequency.getOrDefault(sum - k, 0);

            frequency.put(
                sum,
                frequency.getOrDefault(sum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};

        System.out.println(countPeriods(transactions, 7));
        System.out.println(countPeriods(new int[]{1, 2, 3}, 10));
    }
}