package utils;

public class Operators {
    public static int getPriority(char operator) {
        switch (operator) {
            case ('+'):
            case ('-'):
                return 2;
            case ('*'):
            case ('/'):
                return 3;
            case ('('): return 1;
            case (')'): return 5;
            default: return 0;
        }
    }
    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }
}
