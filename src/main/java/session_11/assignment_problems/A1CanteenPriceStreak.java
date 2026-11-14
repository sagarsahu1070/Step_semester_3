
import java.util.Arrays;
import java.util.Stack;

public class A1CanteenPriceStreak {
    public static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] streak = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            streak[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        return streak;
    }

    public static void main(String[] args) {
        int[] price1 = {100, 80, 60, 70, 60, 75, 85};
        int[] price2 = {10, 20, 30};

        System.out.println(Arrays.toString(priceStreak(price1)));
        System.out.println(Arrays.toString(priceStreak(price2)));
    }
}
