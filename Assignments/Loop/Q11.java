public class Q11 {
    static int calc_fatorial(int n){
        if (n == 0) {
            return 1;
        }

        return n*calc_fatorial(n-1);
    }

    public static void main(String...k){
        System.out.println(calc_fatorial(10));
    }
}
