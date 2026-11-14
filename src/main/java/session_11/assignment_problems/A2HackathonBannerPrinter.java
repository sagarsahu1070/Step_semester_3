
import java.util.Stack;

public class A2HackathonBannerPrinter {
    public static String expandBanner(String code) {
        Stack<Integer> counts = new Stack<>();
        Stack<StringBuilder> builders = new Stack<>();
        StringBuilder current = new StringBuilder();
        int number = 0;

        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            } else if (ch == '[') {
                counts.push(number);
                builders.push(current);
                current = new StringBuilder();
                number = 0;
            } else if (ch == ']') {
                int repeat = counts.pop();
                StringBuilder previous = builders.pop();

                for (int j = 0; j < repeat; j++) {
                    previous.append(current);
                }

                current = previous;
            } else {
                current.append(ch);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println(expandBanner("3[ab]2[c]"));
        System.out.println(expandBanner("2[x3[y]]z"));
    }
}
