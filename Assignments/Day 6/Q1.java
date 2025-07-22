
public class Q1 {

    public static void main(String[] args) {
        int[][] matrix = {
            {5, 8, 3},
            {14, 2, 9},
            {7, 6, 10}
        };

        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }

        System.out.println("Maximum number: " + max);
        System.out.println("Minimum number: " + min);
    }
}