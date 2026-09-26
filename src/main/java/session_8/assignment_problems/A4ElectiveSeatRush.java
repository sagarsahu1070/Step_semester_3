import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
    String getName();
}

class RegularPolicy implements CreditPolicy {

    public int getCreditLimit() {
        return 24;
    }

    public String getName() {
        return "Regular";
    }
}

class HonorsPolicy implements CreditPolicy {

    public int getCreditLimit() {
        return 28;
    }

    public String getName() {
        return "Honors";
    }
}

class ExchangePolicy implements CreditPolicy {

    public int getCreditLimit() {
        return 20;
    }

    public String getName() {
        return "Exchange";
    }
}

class Student {

    private String name;
    private CreditPolicy policy;
    private int currentCredits;

    public Student(
        String name,
        CreditPolicy policy,
        int currentCredits
    ) {

        this.name = name;
        this.policy = policy;
        this.currentCredits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return policy.getCreditLimit();
    }

    public String getType() {
        return policy.getName();
    }

    public boolean canAddCredits(int credits) {
        return currentCredits + credits <=
               policy.getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {

    private String name;
    private int credits;
    private int capacity;

    private List<Student> enrolled =
        new ArrayList<>();

    private Queue<Student> waitlist =
        new LinkedList<>();

    public Elective(
        String name,
        int credits,
        int capacity
    ) {

        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public boolean isEnrolled(Student student) {
        return enrolled.contains(student);
    }

    public boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    public boolean isFull() {
        return enrolled.size() >= capacity;
    }

    public void enroll(Student student) {

        if (isEnrolled(student) ||
            isWaiting(student)) {

            System.out.println(
                "Student already enrolled or waitlisted."
            );

            return;
        }

        if (!student.canAddCredits(credits)) {

            System.out.println(
                "Enrollment failed: " +
                student.getName() +
                " would exceed the " +
                student.getType() +
                " credit limit (" +
                (student.getCurrentCredits() + credits) +
                "/" +
                student.getCreditLimit() +
                ")."
            );

            return;
        }

        if (isFull()) {

            waitlist.add(student);

            System.out.println(
                name +
                " is full. " +
                student.getName() +
                " added to waitlist (position " +
                waitlist.size() +
                ")."
            );

            return;
        }

        enrolled.add(student);
        student.addCredits(credits);

        System.out.println(
            student.getName() +
            " enrolled in " +
            name +
            " (credits: " +
            student.getCurrentCredits() +
            "/" +
            student.getCreditLimit() +
            ")."
        );
    }

    public void drop(Student student) {

        if (!enrolled.remove(student)) {
            return;
        }

        student.removeCredits(credits);

        System.out.println(
            student.getName() +
            " dropped " +
            name +
            " (credits: " +
            student.getCurrentCredits() +
            "/" +
            student.getCreditLimit() +
            ")."
        );

        promote();
    }

    private void promote() {

        if (waitlist.isEmpty()) {
            return;
        }

        Iterator<Student> iterator =
            waitlist.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            if (student.canAddCredits(credits)) {

                iterator.remove();

                enrolled.add(student);
                student.addCredits(credits);

                System.out.println(
                    student.getName() +
                    " promoted from waitlist and enrolled in " +
                    name +
                    " (credits: " +
                    student.getCurrentCredits() +
                    "/" +
                    student.getCreditLimit() +
                    ")."
                );

                return;
            }
        }
    }
}

public class A4ElectiveSeatRush {

    public static void main(String[] args) {

        Elective cloud =
            new Elective(
                "Cloud Computing",
                4,
                2
            );

        Student asha =
            new Student(
                "Asha",
                new RegularPolicy(),
                20
            );

        Student ravi =
            new Student(
                "Ravi",
                new HonorsPolicy(),
                22
            );

        Student neha =
            new Student(
                "Neha",
                new ExchangePolicy(),
                12
            );

        Student kiran =
            new Student(
                "Kiran",
                new RegularPolicy(),
                22
            );

        cloud.enroll(asha);
        cloud.enroll(ravi);
        cloud.enroll(neha);
        cloud.enroll(kiran);

        cloud.drop(asha);
    }
}