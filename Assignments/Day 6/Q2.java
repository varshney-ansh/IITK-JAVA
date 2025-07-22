public class Q2 {

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int primarySum = 0;
        int secondarySum = 0;

        System.out.print("Primary Diagonal: ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][i] + " ");
            primarySum += matrix[i][i];
        }

        System.out.println();
        System.out.print("Secondary Diagonal: ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][matrix.length - 1 - i] + " ");
            secondarySum += matrix[i][matrix.length - 1 - i];
        }

        System.out.println();
        System.out.println("Sum of Primary Diagonal: " + primarySum);
        System.out.println("Sum of Secondary Diagonal: " + secondarySum);
    }
}