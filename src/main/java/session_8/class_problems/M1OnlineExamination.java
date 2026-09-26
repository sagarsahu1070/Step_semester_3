import java.util.*;

interface Question {
    boolean checkAnswer(String answer);
    String getText();
}

class MultipleChoiceQuestion implements Question {
    private String text;
    private String correctAnswer;

    public MultipleChoiceQuestion(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }

    public String getText() {
        return text;
    }
}

class TrueFalseQuestion implements Question {
    private String text;
    private boolean correctAnswer;

    public TrueFalseQuestion(String text, boolean correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }

    public String getText() {
        return text;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public void answerQuestion(int questionNumber, String answer) {

        if (submitted) {
            System.out.println("Cannot change answer after submission.");
            return;
        }

        answers.put(questionNumber, answer);

        System.out.println(
            "Question " + questionNumber +
            " answered with '" + answer + "'."
        );
    }

    public void submit() {

        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;

        examination.setSubmitted(true);

        System.out.println(
            "Examination '" + examination.getTitle() +
            "' submitted successfully."
        );

        int correct = 0;

        for (int i = 0; i < examination.getQuestions().size(); i++) {

            String answer = answers.get(i + 1);

            if (answer != null &&
                examination.getQuestions().get(i).checkAnswer(answer)) {
                correct++;
            }
        }

        System.out.println(
            "Result for '" + examination.getTitle() +
            "' attempt: " +
            correct + "/" +
            examination.getQuestions().size() +
            " correct"
        );
    }
}

class Examination {

    private String title;
    private List<Question> questions = new ArrayList<>();
    private boolean submitted = false;

    public Examination(String title) {
        this.title = title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Attempt start(Student student) {

        if (submitted) {
            System.out.println("Examination already submitted.");
            return null;
        }

        System.out.println(
            "Examination '" + title +
            "' started by " + student.getName() + "."
        );

        return new Attempt(student, this);
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}

public class M1OnlineExamination {

    public static void main(String[] args) {

        Student student = new Student("John Doe");

        Examination exam = new Examination("Math Quiz");

        exam.addQuestion(
            new MultipleChoiceQuestion(
                "2 + 2 = ?",
                "A"
            )
        );

        exam.addQuestion(
            new MultipleChoiceQuestion(
                "Capital of France?",
                "B"
            )
        );

        Attempt attempt = exam.start(student);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();
    }
}