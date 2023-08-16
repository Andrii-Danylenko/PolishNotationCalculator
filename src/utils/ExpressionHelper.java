package utils;

import exceptions.ExpressionSyntaxException;

import java.util.Stack;
public class ExpressionHelper {
    public static boolean validateExpression(String input) throws ExpressionSyntaxException {
        if (containsLetters(input)) {
            throw new ExpressionSyntaxException("Ошибка при проверке выражения! В выражение обнаружены буквы!");
        }
        if (!validateParenthesis(input)) {
            throw new ExpressionSyntaxException("Ошибка при проверке выражения! Незакрытые скобки или скобки без выражения внутри!");
        }
        return true;
    }
    private static boolean containsLetters(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) return true;
        }
        return false;
    }
    private static boolean validateParenthesis(String input) {
        Stack<Character> parenthesisStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                try {
                    if (input.charAt(i + 1) == ')') {
                        return false;
                    }
                } catch (StringIndexOutOfBoundsException ignore) {

                }
                parenthesisStack.push(ch);
            }
            else if (ch == ')') {
                if (parenthesisStack.isEmpty()) {
                    return false;
                }
                parenthesisStack.pop();
            }
        }
        return parenthesisStack.isEmpty();
    }
    public static String prepareStatement(String input) {
        input = input.replaceAll("([^()\\w])\\1+", "$1");
        input =  input.replaceAll("(?<=\\d),(?=\\d)|[.,](?![\\d.,])", ".");
        return input;
    }
}
