import utils.InputListener;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            /* 1 + (3 - 2) * 6           Пример для проверки работы с числами первого порядка
               100 + (3.2 - 2.3) * 0.1   Пример для проверки работы с числами второго+ порядка совместно с числами с плавающей точкой
               100 + (3.2 - 2.3) * -0.1  Пример для проверки работы с отрицательными числами
               a + ( b - c ) * d         Входная строка
               a b c - d * +             Ожидаемый результат
             */
            InputListener inputListener = new InputListener();
            inputListener.listenInput();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Something went wrong!");
        }
    }
}