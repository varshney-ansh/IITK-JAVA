import java.util.*;

public class Q6 {
    void printTable(int n){
        System.out.println("Printing Multplication Table Of " + n);
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " x " + n + " = " + n*i);
        }
    }
    
    public static void main(String...k){
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter number : ");
        int n = cs.nextInt();
        Q6 obj = new Q6();
        obj.printTable(n);
    }
}
