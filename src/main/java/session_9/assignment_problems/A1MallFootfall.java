import java.util.*;

public class A1MallFootfall {

    static int[] footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;
        int[] prefix = new int[n + 1];
        int[] result = new int[queries.length];

        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + visitors[i];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            result[i] = prefix[end + 1] - prefix[start];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};

        System.out.println(Arrays.toString(
            footfallReport(visitors, queries)
        ));
    }
}