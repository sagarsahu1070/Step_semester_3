package oop.assigment_problems;

class BrokenLibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(
            String name,
            String memberId,
            int booksIssued) {

        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 0;

    LibraryMember(
            String name,
            int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId =
                "LM-"
                + (1000 + memberCount);
    }

    void printMemberCard() {

        System.out.println(
                name + " | " + memberId
        );
    }

    static void printTotalMembers() {

        System.out.println(
                "Total members: "
                + memberCount
        );
    }
}

public class LibraryMembershipSystem {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember aditi =
                new BrokenLibraryMember(
                        "Aditi",
                        "LM-1001",
                        2
                );

        BrokenLibraryMember rohan =
                new BrokenLibraryMember(
                        "Rohan",
                        "LM-1002",
                        3
                );

        System.out.println(aditi.name);
        System.out.println(rohan.name);

        /*
         * name must not be static because every member
         * has a different name.
         *
         * memberId must not be static because every member
         * must have a different ID.
         *
         * booksIssued must not be static because each member
         * can issue a different number of books.
         *
         * Static fields are shared by every object, so the
         * second member overwrites the first member's data.
         */

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember member1 =
                new LibraryMember("Aditi", 2);

        LibraryMember member2 =
                new LibraryMember("Rohan", 3);

        member1.printMemberCard();
        member2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}