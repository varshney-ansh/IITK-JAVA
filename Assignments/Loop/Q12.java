import java.util.*;
public class Q12 {
    static int count_digits(int number){
        String strNum = String.valueOf(number);
        int digit_count = strNum.length();
        return digit_count;
    }

    public static void main(String...k){
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter a Number : ");
        int number = cs.nextInt();

        if (number < 0) {
            System.out.println("Armstrong Number can't be a negative;");
        }else{
            int no_of_digits = count_digits(number);
            
            String str_num = String.valueOf(number);
            int sum = 0;
            for(int i = 0; i < no_of_digits; i++){
                int raisedPower = (int) Math.pow((int)str_num.charAt(i) - '0', no_of_digits);
                sum += raisedPower;
            }
            if (sum == number) {
                System.out.println("Yes, it's a Armstrong number;");
            }else{
                System.out.println("It's not a Armstong number;");
            }

        }
    }

}
