package constructors.assigment_problems;

public class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode,
                   String canteenName,
                   int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode,
                   String canteenName) {

        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        // Higher trust score comes first
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore,
                    this.trustScore);
        }

        // Case-insensitive canteen code comparison
        int codeResult =
                this.canteenCode.compareToIgnoreCase(
                        other.canteenCode);

        if (codeResult != 0) {
            return codeResult;
        }

        // If codes are same, shorter name comes first
        return Integer.compare(
                this.canteenName.length(),
                other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        // Manual selection sort
        for (int i = 0; i < result.length - 1; i++) {

            int bestIndex = i;

            for (int j = i + 1; j < result.length; j++) {

                if (result[j].compareTo(result[bestIndex]) < 0) {
                    bestIndex = j;
                }
            }

            Canteen temp = result[i];
            result[i] = result[bestIndex];
            result[bestIndex] = temp;
        }

        return result;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public int getTrustScore() {
        return trustScore;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen c : ranked) {
            System.out.println(
                    c.getCanteenCode() + " - "
                    + c.getCanteenName() + " - "
                    + c.getTrustScore());
        }
    }
}