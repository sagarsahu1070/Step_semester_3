public class A2LongestBudgetStreak {

    static int[] longestStreak(int[] costs, int budget) {
        int left = 0, sum = 0;
        int maxLength = 0, startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (sum > budget) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > maxLength) {
                maxLength = length;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};

        int[] result = longestStreak(costs, 8);
        System.out.println("(" + result[0] + ", " + result[1] + ")");

        int[] result2 = longestStreak(new int[]{9, 10}, 8);
        System.out.println("(" + result2[0] + ", " + result2[1] + ")");
    }
}