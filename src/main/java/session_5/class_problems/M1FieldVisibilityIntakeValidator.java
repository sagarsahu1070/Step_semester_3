public class M1FieldVisibilityIntakeValidator {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected"))
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    static class PatientRecord {

        private String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;

        public PatientRecord(String patientId, String wardCode,
                             double vitalsScore, String facilityName) {

            String id = patientId == null ? "" : patientId.trim();

            if (id.isEmpty() || id.length() < 4)
                throw new IllegalArgumentException("construction rejected");

            this.patientId = id;
            this.wardCode = wardCode;
            this.vitalsScore = vitalsScore;
            this.facilityName = facilityName;
        }
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("default", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));

        try {
            new PatientRecord("MT9", "W3", 98.2,
                    "MediTrack Central");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        PatientRecord p =
            new PatientRecord("MT94", "W3", 98.2,
                    "MediTrack Central");

        System.out.println("PatientRecord created successfully");
    }
}