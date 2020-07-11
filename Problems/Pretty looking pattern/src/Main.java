import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Instantiation
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = new char[4][];

        // Input
        for (int i = 0; i < 4; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        // Processing
        boolean pretty = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (is2By2SecOf4By4MatrixSame(matrix, i, j)) {
                    pretty = false;
                }
            }
        }

        // Output
        if (pretty) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean is2By2SecOf4By4MatrixSame(char[][] matrix, int rowsFromTop, int columnsFromLeft) {
        if (matrix[rowsFromTop][columnsFromLeft] == matrix[rowsFromTop][columnsFromLeft + 1] &&
                matrix[rowsFromTop][columnsFromLeft + 1] == matrix[rowsFromTop + 1][columnsFromLeft] &&
                matrix[rowsFromTop + 1][columnsFromLeft + 1] == matrix[rowsFromTop + 1][columnsFromLeft]) {
            return true;
        } else {
            return false;
        }
    }
}