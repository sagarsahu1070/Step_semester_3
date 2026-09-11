package session_5.assignment_problems;

public class Problem1MembershipFieldReachChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {"private", "default", "protected", "public"};
        String result = "";

        for (String modifier : modifiers) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equals(modifier)) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (!result.equals(""))
                result += " | ";

            result += modifier + ": " + allowed +
                    " allowed / " + denied + " denied";
        }

        return result;
    }

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        double finesOwed;
        public String displayName;

        LibraryMember(String membershipId, String branchCode,
                      double finesOwed, String displayName) {

            String id = membershipId.trim();

            if (id.isEmpty() || id.length() < 4)
                throw new IllegalArgumentException("Invalid membership ID");

            this.membershipId = id;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS"));

        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        new LibraryMember("LB94", "BR1", 0, "Priya Nair");
        System.out.println("LB94 construction successful");
    }
}