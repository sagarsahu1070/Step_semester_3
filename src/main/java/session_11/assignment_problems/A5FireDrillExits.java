
import java.util.Arrays;

public class A5FireDrillExits {
    public static int[][] exitDistance(char[][] plan) {
        if (plan.length == 0) {
            return new int[0][0];
        }

        int rows = plan.length;
        int cols = plan[0].length;
        int[][] distance = new int[rows][cols];

        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        int[] queue = new int[rows * cols];
        int head = 0;
        int tail = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    distance[r][c] = 0;
                    queue[tail++] = r * cols + c;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (head < tail) {
            int cell = queue[head++];
            int r = cell / cols;
            int c = cell % cols;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < rows
                        && nc >= 0 && nc < cols
                        && plan[nr][nc] != '#'
                        && distance[nr][nc] == -1) {

                    distance[nr][nc] = distance[r][c] + 1;
                    queue[tail++] = nr * cols + nc;
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int[] row : result) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    System.out.print(" ");
                }
                System.out.print(row[j]);
            }
            System.out.println();
        }
    }
}
