import java.util.*;

interface ScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculateScore(double idea, double execution, double presentation) {
        return idea * 0.50 + execution * 0.30 + presentation * 0.20;
    }
}

class OpenScoring implements ScoringRule {
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;
    private Hackathon hackathon;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean alreadyRegistered(Hackathon h) {
        return hackathon == h;
    }

    public void setHackathon(Hackathon h) {
        hackathon = h;
    }
}

class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Team {
    private String name;
    private List<Student> members;
    private ScoringRule scoringRule;
    private Project project;
    private double finalScore;
    private boolean scored = false;

    public Team(String name, List<Student> members, ScoringRule rule) {
        this.name = name;
        this.members = members;
        this.scoringRule = rule;
    }

    public String getName() {
        return name;
    }

    public boolean hasMember(Student student) {
        return members.contains(student);
    }

    public void submitProject(Project project) {
        if (this.project != null) {
            System.out.println("Project already submitted.");
            return;
        }

        this.project = project;

        System.out.println(
            "Project '" + project.getName() +
            "' submitted by " + name + "."
        );
    }

    public void score(
        double idea,
        double execution,
        double presentation,
        boolean published
    ) {

        if (published) {
            System.out.println(
                "Rescore rejected: Results have already been published."
            );
            return;
        }

        finalScore =
            scoringRule.calculateScore(
                idea,
                execution,
                presentation
            );

        scored = true;

        System.out.println("Score recorded for '" +
                           project.getName() + "'.");

        System.out.printf(
            "Final score: %.2f%n",
            finalScore
        );
    }
}

class Hackathon {

    private String name;
    private List<Team> teams = new ArrayList<>();
    private boolean published = false;

    public Hackathon(String name) {
        this.name = name;
    }

    public boolean registerTeam(
        Team team,
        List<Student> students
    ) {

        if (students.size() < 2 || students.size() > 4) {
            System.out.println(
                "Registration failed: A team must have 2 to 4 members."
            );
            return false;
        }

        for (Student student : students) {
            if (student.alreadyRegistered(this)) {
                System.out.println(
                    "Registration failed: " +
                    student.getName() +
                    " is already in a team."
                );
                return false;
            }
        }

        for (Student student : students) {
            student.setHackathon(this);
        }

        teams.add(team);

        System.out.println(
            "Team " + team.getName() +
            " registered (" +
            students.size() +
            " members)."
        );

        return true;
    }

    public void publishResults() {
        published = true;
        System.out.println("Results published.");
    }

    public boolean isPublished() {
        return published;
    }
}

public class A1CodeSprintJudging {

    public static void main(String[] args) {

        Hackathon hackathon =
            new Hackathon("Code Sprint");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters =
            new Team(
                "ByteBusters",
                Arrays.asList(asha, ravi, neha),
                new InnovationScoring()
            );

        hackathon.registerTeam(
            byteBusters,
            Arrays.asList(asha, ravi, neha)
        );

        Team soloCoder =
            new Team(
                "SoloCoder",
                Arrays.asList(kiran),
                new OpenScoring()
            );

        hackathon.registerTeam(
            soloCoder,
            Arrays.asList(kiran)
        );

        byteBusters.submitProject(
            new Project("SmartAttend")
        );

        byteBusters.score(
            8,
            7,
            9,
            hackathon.isPublished()
        );

        hackathon.publishResults();

        byteBusters.score(
            10,
            7,
            9,
            hackathon.isPublished()
        );
    }
}