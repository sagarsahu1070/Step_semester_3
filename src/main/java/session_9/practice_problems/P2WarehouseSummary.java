public class P2WarehouseSummary {

    static void warehouseSummary(int[][] grid) {
        int total = 0;
        int max = grid[0][0];
        int row = 0, col = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];

                if (grid[i][j] > max) {
                    max = grid[i][j];
                    row = i;
                    col = j;
                }
            }
        }

        System.out.println("Total = " + total);
        System.out.println(
            "Max = " + max + ", Coordinate = (" + row + ", " + col + ")"
        );
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        warehouseSummary(grid);
    }
}