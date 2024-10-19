import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть рядок: ");
        String input = scanner.nextLine();

        int[] counts = new int[255];
        for (char c : input.toCharArray()) {
            counts[c]++;
        }

        Character[] charCounts = new Character[input.length()];
        int index = 0;

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                charCounts[index++] = new Character((char) i, counts[i]);
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.println("Символ: '" + charCounts[i].character + "' Кількість: " + charCounts[i].count);
        }

        scanner.close();
    }
}
