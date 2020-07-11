import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // Instantiation
        Scanner scanner = new Scanner(System.in);
        String[] rows = new String[scanner.nextInt()];
        scanner.nextLine(); // To place the cursor at the correct position

        // Input
        for (int i = 0; i < rows.length; i++) {
            rows[i] = scanner.nextLine().replaceAll(" ", "");
        }
        int k = scanner.nextInt();

        // Processing
        char[] charArrayToFind = new char[k];
        for (int i = 0; i < charArrayToFind.length; i++) {
            charArrayToFind[i] = '0';
        }
        String strToFind = new String(charArrayToFind);

        int rowWithKAvailableSeats = 0;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i].contains(strToFind)) {
                rowWithKAvailableSeats = i + 1;
                break;
            }
        }

        // Output
        System.out.println(rowWithKAvailableSeats);
    }
}