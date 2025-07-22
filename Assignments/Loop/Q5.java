import java.util.*;

class Q5 {
    void sumOfNaturalNumber(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i%2 == 0) {
                sum += i;
            }
        }
        System.out.println("Sum : " + sum);
    }

    public static void main(String...k){
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter nth Number : ");
        int n = cs.nextInt();
        Q5 obj = new Q5();
        obj.sumOfNaturalNumber(n);
    }
}
