public class Task5 {
    public static void main(String[] args) {
        String[][] matrix = {
                {"Skoda", "Audi", "Kia"},
                {"Ceed", "Bmw", "Volvo"},
                {"Audi", "Volvo", "Audi"}
        };

        String substring = "Audi";
        int count = countSubstringOccurrences(matrix, substring);

        System.out.println("Кількість входжень \"" + substring + "\": " + count);
    }

    public static int countSubstringOccurrences(String[][] matrix, String substring) {
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String currentString = matrix[i][j];
                count += countOccurrences(currentString, substring);
            }
        }

        return count;
    }

    public static int countOccurrences(String str, String substring) {
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count;
    }
}
