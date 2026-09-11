package session_5.assignment_problems;

public class Problem4LibraryMemberJavaBean {

    static class LibraryMember {

        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswer;

        public LibraryMember() {
            this(null, null);
        }

        public LibraryMember(String name) {
            this(null, name);
        }

        public LibraryMember(String membershipId, String name) {
            this.membershipId = membershipId;
            this.name = name;
            this.premiumMember = false;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {

            // Write-once property
            if (this.membershipId == null) {
                this.membershipId = id;
            }
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premium) {
            this.premiumMember = premium;
        }

        public void setSecurityAnswer(String answer) {

            // Store a deterministic one-way value
            this.securityAnswer =
                    Integer.toHexString(answer.hashCode());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        LibraryMember m1 =
                new LibraryMember("Priya Nair");

        System.out.println(
                m1.getMembershipId());

        LibraryMember m2 =
                new LibraryMember(
                        "LIB-8841", "Priya Nair");

        System.out.println(
                m2.getMembershipId());

        LibraryMember m3 =
                new LibraryMember();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(
                m3.getMembershipId());

        m3.setSecurityAnswer("blue");
    }
}