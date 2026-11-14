
import java.util.Stack;

public class A3QuizScoringFormula {
    private static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return 0;
    }

    private static void apply(Stack<Integer> values,
                              Stack<Character> operators) {
        char op = operators.pop();
        int b = values.pop();
        int a = values.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;
            case '-':
                values.push(a - b);
                break;
            case '*':
                values.push(a * b);
                break;
            case '/':
                values.push(a / b);
                break;
        }
    }

    public static int evaluate(String expr) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expr.length()) {
            char ch = expr.charAt(i);

            if (Character.isWhitespace(ch)) {
                i++;
            } else if (Character.isDigit(ch)) {
                int number = 0;

                while (i < expr.length()
                        && Character.isDigit(expr.charAt(i))) {
                    number = number * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                values.push(number);
            } else if (ch == '(') {
                operators.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operators.isEmpty()
                        && operators.peek() != '(') {
                    apply(values, operators);
                }

                operators.pop();
                i++;
            } else {
                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    apply(values, operators);
                }

                operators.push(ch);
                i++;
            }
        }

        while (!operators.isEmpty()) {
            apply(values, operators);
        }

        return values.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}
