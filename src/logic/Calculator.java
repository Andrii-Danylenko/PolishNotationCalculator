package logic;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal calculate(String input);
    StringBuilder getPolishNotation(String input);
    BigDecimal getResult(StringBuilder polishNotation);
    BigDecimal applyAnOperation(char operator, BigDecimal lowerNum, BigDecimal upperNum);
}
