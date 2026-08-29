package oop.class_problems;

class BrokenStudent {

    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(
            String name,
            String regNo,
            int attendance) {

        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }
}

class FixedSrmStudent {

    String name;
    String regNo;
    int attendance;

    static String university = "SRM";
    static int admissionCount = 0;

    FixedSrmStudent(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        this.regNo =
                "RA2311003010"
                + String.format("%02d", admissionCount);
    }

    void printIdCard() {

        System.out.println(
                name + " | " + regNo
        );
    }

    static void printTotalAdmissions() {

        System.out.println(
                "Students admitted so far: "
                + admissionCount
        );
    }
}

public class StaticInstanceDemo {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenStudent ravi =
                new BrokenStudent(
                        "Ravi",
                        "RA231100301011",
                        82
                );

        BrokenStudent meera =
                new BrokenStudent(
                        "Meera",
                        "RA231100301012",
                        74
                );

        System.out.println(ravi.name);
        System.out.println(meera.name);

        /*
         * Static fields are shared by all objects.
         *
         * name is wrong as static because every student
         * needs an independent name.
         *
         * regNo is wrong as static because every student
         * must have a different registration number.
         *
         * attendance is wrong as static because each student's
         * attendance is different.
         */

        System.out.println();
        System.out.println("Fixed version:");

        FixedSrmStudent student1 =
                new FixedSrmStudent("Ravi", 82);

        FixedSrmStudent student2 =
                new FixedSrmStudent("Meera", 74);

        student1.printIdCard();
        student2.printIdCard();

        FixedSrmStudent.printTotalAdmissions();
    }
}