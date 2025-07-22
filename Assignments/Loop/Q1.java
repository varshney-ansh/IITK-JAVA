import java.util.*;

class Q1 {
    void printAllNaturalNumbers(int n) {
        if (n != 0 && n > 0) {
            System.out.println("All Natural Numbers are : ");
            for (int i = 1; i <= n; i++) {
                System.out.println(i);
            }
        }else{
            System.out.println("Invalid Number");
        }
    }

    public static void main(String...k){
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter the nth Number : ");
        int n = cs.nextInt();

        Q1 obj = new Q1();
        obj.printAllNaturalNumbers(n);
    }
}
