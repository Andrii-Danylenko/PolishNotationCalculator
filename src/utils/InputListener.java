package utils;

import exceptions.ExpressionSyntaxException;
import logic.CalculatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputListener {
    private final BufferedReader reader;
    private final CalculatorImpl calculator;
    public InputListener() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        calculator = new CalculatorImpl();
    }
    public void listenInput() throws IOException {
        while (true) {
            System.out.println("Введите выражение, которое хотите посчитать: ");
            String input = reader.readLine();
            try {
                System.out.println(input + " = " + calculator.calculate(input));
            } catch (ExpressionSyntaxException | ArithmeticException exception) {
                System.out.println(exception.getMessage() + " Попробуйте ввести выражение заново.");
            }
        }
    }
}
