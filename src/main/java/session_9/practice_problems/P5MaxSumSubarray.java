public class P5MaxSumSubarray {

    static int maxSumSubarray(int[] sales, int k) {
        if (k < 1 || k > sales.length)
            throw new IllegalArgumentException("Invalid k");

        int sum = 0;

        for (int i = 0; i < k; i++)
            sum += sales[i];

        int max = sum;

        for (int i = k; i < sales.length; i++) {
            sum = sum - sales[i - k] + sales[i];

            if (sum > max)
                max = sum;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};

        System.out.println(maxSumSubarray(sales, 3));
    }
}