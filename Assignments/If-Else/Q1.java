import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        if (a > b) {
            System.out.println("Maximum is: " + a);
        } else if (b > a) {
            System.out.println("Maximum is: " + b);
        } else {
            System.out.println("Both numbers are equal.");
        }
    }
}