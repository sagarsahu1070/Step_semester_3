package oop.class_problems;

public class SrmStudentAttendance {

    String name;
    String regNo;
    int attendance;

    SrmStudentAttendance(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    /*
     * isEligible() is an instance method because eligibility belongs
     * to one particular student's attendance.
     *
     * classAverage() is static because it calculates one value
     * using the complete array of students, not one student.
     */
    static double classAverage(SrmStudentAttendance[] students) {

        int total = 0;

        for (SrmStudentAttendance student : students) {
            total += student.attendance;
        }

        return (double) total / students.length;
    }

    public static void main(String[] args) {

        SrmStudentAttendance[] students = {
                new SrmStudentAttendance("Ravi", "RA231100301011", 82),
                new SrmStudentAttendance("Anitha", "RA231100301012", 68),
                new SrmStudentAttendance("Karthik", "RA231100301013", 91),
                new SrmStudentAttendance("Meera", "RA231100301014", 74),
                new SrmStudentAttendance("Suresh", "RA231100301015", 60)
        };

        for (SrmStudentAttendance student : students) {

            String status = student.isEligible()
                    ? "Eligible"
                    : "Detained";

            System.out.println(
                    student.name + " - "
                    + student.attendance + "% - "
                    + status
            );
        }

        double average =
                SrmStudentAttendance.classAverage(students);

        System.out.println(
                "Class average: " + average + "%"
        );
    }
}