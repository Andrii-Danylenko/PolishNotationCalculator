package logic;

import exceptions.CalculationException;
import exceptions.ExpressionSyntaxException;
import utils.ExpressionHelper;
import utils.Operators;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Stack;

public class CalculatorImpl implements Calculator {
    // Кеширование результатов вычислений для устранения повторных вычислений
    private final HashMap<String, BigDecimal> calculatedValues = new HashMap<>();

    @Override
    public BigDecimal calculate(String input) {
        if (calculatedValues.containsKey(input)) {
            return calculatedValues.get(input);
        }
        StringBuilder polishNotation = getPolishNotation(input);
        try {
            BigDecimal result = getResult(polishNotation);
            calculatedValues.putIfAbsent(input, result);
            return result;
        } catch (ArithmeticException exception) {
            throw new ArithmeticException("Деление на ноль.");
        }
    }

    @Override
    public StringBuilder getPolishNotation(String input) {
        if (!ExpressionHelper.validateExpression(input)) {
            throw new ExpressionSyntaxException("Ошибка в синтаксисе!");
        }
        input = ExpressionHelper.prepareStatement(input);
        Stack<Character> operations = new Stack<>();
        StringBuilder polishNotation = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '-' && Character.isDigit(input.charAt(i + 1)) || Character.isDigit(ch)) {
                StringBuilder number = new StringBuilder();
                while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.' || input.charAt(i) == '-')) {
                    number.append(input.charAt(i));
                    i++;
                }
                polishNotation.append(number).append(' ');
                i--;
            } else if (Operators.isOperator(ch)) {
                System.out.println(ch);
                if (ch == '(') {
                    operations.push(ch);
                } else if (ch == ')') {
                    while (!operations.isEmpty()) {
                        if (operations.peek() == '(') {
                            operations.pop();
                            break;
                        }
                        polishNotation.append(operations.pop()).append(' ');
                    }
                } else if (!operations.isEmpty() && Operators.getPriority(operations.peek()) >= Operators.getPriority(ch)) {
                    while (!operations.isEmpty() && Operators.getPriority(operations.peek()) >= Operators.getPriority(ch)) {
                        polishNotation.append(operations.pop()).append(' ');
                    }
                } else if (operations.isEmpty() || Operators.getPriority(operations.peek()) <= Operators.getPriority(ch)) {
                    operations.push(ch);
                }
            }
        }
        while (!operations.isEmpty()) {
            polishNotation.append(operations.pop()).append(' ');
        }
        return polishNotation;
    }

    @Override
    public BigDecimal getResult(StringBuilder polishNotation) {
        Stack<BigDecimal> resultStack = new Stack<>();
        int i = 0;
        while (i < polishNotation.length()) {
            char ch = polishNotation.charAt(i);
            if (ch != ' ') {
                if (Character.isDigit(ch) || (ch == '-' && i + 1 < polishNotation.length() && Character.isDigit(polishNotation.charAt(i + 1)))) {
                    StringBuilder number = new StringBuilder();
                    while (i < polishNotation.length() && polishNotation.charAt(i) != ' ') {
                        char innerChar = polishNotation.charAt(i);
                        number.append(innerChar);
                        i++;
                    }
                    resultStack.push(new BigDecimal(number.toString()));
                } else {
                    BigDecimal lowerNum = resultStack.pop();
                    BigDecimal upperNum = resultStack.pop();
                    resultStack.push(applyAnOperation(ch, lowerNum, upperNum));
                }
            }
            i++;
        }

        if (resultStack.isEmpty()) {
            throw new CalculationException("Ошибка при вычислении выражения.");
        }
        return resultStack.pop();
    }

    @Override
    public BigDecimal applyAnOperation(char operator, BigDecimal lowerNum, BigDecimal upperNum) throws ArithmeticException {
        switch (operator) {
            case ('+'):
                return upperNum.add(lowerNum, MathContext.UNLIMITED);
            case ('-'):
                return upperNum.subtract(lowerNum, MathContext.UNLIMITED);
            case ('*'):

                return upperNum.multiply(lowerNum, MathContext.UNLIMITED);
            default:
                return upperNum.divide(lowerNum, MathContext.UNLIMITED);
        }
    }
}