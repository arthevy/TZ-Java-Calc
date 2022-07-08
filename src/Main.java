import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        printHelpText();

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String rawExpression = scanner.nextLine();

            rawExpression = rawExpression.replaceAll("\\s", "");

            System.out.println(calc(rawExpression));
        }
    }

    public static String calc(String rawExpression) {
        String num1;
        String num2;

        String Arabian = "12345678910";
        String Romanian = "IIIVIIIX";

        Pattern operate = Pattern.compile("[/*+-]");
        Matcher operator = operate.matcher(rawExpression);
        int count = 0;
        while (operator.find())
            count++;
        if (count>=2) {
            throw new IllegalArgumentException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (rawExpression.contains("-")) {
            String[] splitExpression = rawExpression.split("-");
            num1 = splitExpression[0];
            num2 = splitExpression[1];
        } else if (rawExpression.contains("+")) {
            String[] splitExpression = rawExpression.split("[+]");
            num1 = splitExpression[0];
            num2 = splitExpression[1];
        } else if (rawExpression.contains("*")) {
            String[] splitExpression = rawExpression.split("[*]");
            num1 = splitExpression[0];
            num2 = splitExpression[1];
        } else if (rawExpression.contains("/")) {
            String[] splitExpression = rawExpression.split("/");
            num1 = splitExpression[0];
            num2 = splitExpression[1];
        } else {
            throw new IllegalArgumentException("Математическая операция не найдена");
        }

        if (Arabian.contains(num1) && Arabian.contains(num2)) {
            return Count.countArabian(num1, num2, rawExpression);
        } else if (Romanian.contains(num1) && Romanian.contains(num2)) {
            return Count.countRoman(num1, num2, rawExpression);
        } else if ((Arabian.contains(num1) && Romanian.contains(num2)) || (Romanian.contains(num1) && Arabian.contains(num2))) {
            throw new IllegalArgumentException("т.к. используются одновременно разные системы счисления");
        } else {
            throw new IllegalArgumentException("Введено неверное значение");
        }
    }

    private static void printHelpText() {
        System.out.println(
                "Тестовое задание - Калькулятор:\n" +
                        "1. Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку.\n" +
                        "2. Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.\n" +
                        "3. Калькулятор принимает на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.\n" +
                        "4. Калькулятор умеет работать только с целыми числами. \n" +
                        "5. Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу. \n" +
                        "6. При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.\n" +
                        "7. При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.\n" +
                        "8. При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.\n" +
                        "9. Результатом операции деления является целое число, остаток отбрасывается.\n" +
                        "10.1 Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.\n" +
                        "10.2 Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение.\n" +
                        "\n->> Введите данные (например 1+5, II-V, 5/2):");
    }
}