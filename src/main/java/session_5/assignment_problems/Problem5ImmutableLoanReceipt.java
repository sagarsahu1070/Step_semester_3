package session_5.assignment_problems;

public class Problem5ImmutableLoanReceipt {

    public static final class LoanReceipt {

        private final String memberId;
        private final String[] bookIds;

        static {
            // One-time class-level setup
            System.out.println("Loan ledger initialized");
        }

        public LoanReceipt(String memberId, String[] bookIds) {

            if (bookIds == null)
                throw new IllegalArgumentException(
                        "Book IDs cannot be null");

            for (String id : bookIds) {

                if (id == null ||
                    !id.matches("BK-\\d{3}")) {

                    throw new IllegalArgumentException(
                            "Invalid book ID");
                }
            }

            this.memberId = memberId;

            // Defensive copy
            this.bookIds = bookIds.clone();
        }

        public String[] getBookIds() {

            // Defensive copy
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(
                int index, String newId) {

            if (index < 0 || index >= bookIds.length)
                throw new IndexOutOfBoundsException();

            if (newId == null ||
                !newId.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                        "Invalid book ID");
            }

            String[] corrected = bookIds.clone();
            corrected[index] = newId;

            // Return a new object
            return new LoanReceipt(memberId, corrected);
        }
    }

    static class ReferenceOnlyLoanReceipt
            extends LoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null)
            return "0 processed | 0 null skipped | "
                    + "0 reference-only | 0 regular";

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        try {

            new LoanReceipt(
                    "LIB-8841",
                    new String[]{"BK-100", "bad"});

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"});

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"),

            null,

            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"})
        };

        System.out.println(
                processNightlyCirculation(receipts));
    }
}