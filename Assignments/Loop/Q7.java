import java.util.Scanner;

public class Q7 {
    public static void main(String ...k){
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter a Number: ");
        int n = cs.nextInt();
        if (n < 0) {
            System.out.println("No. Of Digits: " + ((String.valueOf(n).length())-1));
        }else{
            System.out.println("No. Of Digits: " + String.valueOf(n).length());
        }
    }
}