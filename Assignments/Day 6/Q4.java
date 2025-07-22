
import java.util.Arrays;

public class Q4 {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 3};

        Arrays.sort(arr);

        System.out.print("Sorted Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}