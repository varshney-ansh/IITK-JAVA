import java.util.*;

class Q4 {
    void sumOfNaturalNumber(int n){
        int sum = n*(n+1)/2;
        System.out.println("Sum: " + sum);
    }

    public static void main(String...k){
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter nth Number : ");
        int n = cs.nextInt();
        Q4 obj = new Q4();
        obj.sumOfNaturalNumber(n);
    }
}
