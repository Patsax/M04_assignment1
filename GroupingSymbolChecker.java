import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GroupingSymbolChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <input_file>");
            return;
        }

        String filename = args[0];
        if (checkGroupingSymbols(filename)) {
            System.out.println("Correct grouping symbols.");
        } else {
            System.out.println("Incorrect grouping symbols.");
        }
    }

    public static boolean checkGroupingSymbols(String filename) {
        Stack<Character> stack = new Stack<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (isOpeningSymbol(ch)) {
                        stack.push(ch);
                    } else if (isClosingSymbol(ch)) {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            return false; // Mismatched or no opening symbol
                        }
                    }
                }
            }
            return stack.isEmpty(); // All symbols should be matched
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error reading the file
        }
    }

    public static boolean isOpeningSymbol(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    public static boolean isClosingSymbol(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }
}