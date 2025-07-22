import java.util.Scanner;

public class Q8 {
    public static void main(String... k) {
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter a number : ");
        int number = cs.nextInt();
        String strNum = String.valueOf(number);
        int digit_count = strNum.length();
        int digit_sum = 0;

        if (number < 0) {
            for (int i = 1; i < digit_count; i++) {
                digit_sum += (int) strNum.charAt(i) - '0';
            }
        } else {
            for (int i = 0; i < digit_count; i++) {
                digit_sum += (int) strNum.charAt(i) - '0';
            }
        }

        System.out.println("Sum Of Digits : " + digit_sum);
    }
}
