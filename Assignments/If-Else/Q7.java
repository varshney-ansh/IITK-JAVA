import java.util.Scanner;

public class Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a character: ");
        char ch = sc.next().charAt(0);

        if (ch >= 'A' && ch <= 'Z') {
            System.out.println("Uppercase alphabet");
        } else if (ch >= 'a' && ch <= 'z') {
            System.out.println("Lowercase alphabet");
        } else {
            System.out.println("Not an alphabet");
        }
    }
}