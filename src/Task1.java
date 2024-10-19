import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ціле число: ");
        int number = scanner.nextInt();

        String binary = toBinary(number);
        System.out.println("Двійковий вигляд: " + binary);
    }

    public static String toBinary(int number) {
        StringBuilder binary = new StringBuilder();

        if (number == 0) {
            return "0";
        }

        while (number > 0) {
            int remainder = number % 2;
            binary.insert(0, remainder);
            number = number / 2;
        }

        return binary.toString();
    }
}
