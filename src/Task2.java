import java.util.Scanner;

public class Task2 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть простий вираз (наприклад: 2+3):");
            String input = scanner.nextLine();

            char operation = 0;
            int operatorIndex = -1;

            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    operation = ch;
                    operatorIndex = i;
                    break;
                }
            }

            if (operatorIndex == -1) {
                System.out.println("Оператор не знайдений!");
                return;
            }

            double leftOperand = Double.parseDouble(input.substring(0, operatorIndex));
            double rightOperand = Double.parseDouble(input.substring(operatorIndex + 1));

            double result = 0;
            switch (operation) {
                case '+':
                    result = leftOperand + rightOperand;
                    break;
                case '-':
                    result = leftOperand - rightOperand;
                    break;
                case '*':
                    result = leftOperand * rightOperand;
                    break;
                case '/':
                    if (rightOperand != 0) {
                        result = leftOperand / rightOperand;
                    } else {
                        System.out.println("Ділення на нуль неможливе.");
                        return;
                    }
                    break;
            }
            
            System.out.println("Результат: " + result);
        }
    }
