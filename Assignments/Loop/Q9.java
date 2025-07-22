import java.util.Scanner;

public class Q9 {
    public static void main(String... k) {
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter a Number: ");
        int number = cs.nextInt();
        String num = String.valueOf(number);
        StringBuilder revNumber = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            revNumber.append(String.valueOf(num.charAt(i)));
        }
        System.out.println("Reverse Number: " + revNumber);
    }
}