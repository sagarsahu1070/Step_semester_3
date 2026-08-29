package string.assigment_problems;

public class MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {

        String[] words = review.split("\\s+");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (String word : words) {

            int length = word.length();

            if (length <= 4) {

                shortWords++;

            } else if (length <= 8) {

                mediumWords++;

            } else {

                longWords++;
            }
        }

        System.out.println("Short: " + shortWords);
        System.out.println("Medium: " + mediumWords);
        System.out.println("Long: " + longWords);
    }

    public static void main(String[] args) {

        String review =
                "This movie was absolutely fantastic and thrilling";

        classifyWordLengths(review);
    }
}