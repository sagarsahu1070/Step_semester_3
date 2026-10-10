public class P3StudentMarksGrid {
    public static void main(String[] args) {
        String[] names = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};

        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < marks[i].length; j++) {
                if (marks[i][j] != -1)
                    total += marks[i][j];
            }

            System.out.println("Total " + names[i] + " " + total);
        }

        for (int j = 0; j < subjects.length; j++) {
            int max = -1;
            int topper = -1;

            for (int i = 0; i < names.length; i++) {
                if (marks[i][j] != -1 && marks[i][j] > max) {
                    max = marks[i][j];
                    topper = i;
                }
            }

            System.out.println(
                "Topper " + subjects[j] + " " +
                names[topper] + " " + max
            );
        }
    }
}