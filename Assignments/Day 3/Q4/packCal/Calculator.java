package packCal;

import packAdd.Add;
import packSub.Sub;
import packMulti.Multi;
import packDiv.Div;

public class Calculator {
    public static void main(String[] args) {
        int a = 20, b = 10;

        Add add = new Add();
        Sub sub = new Sub();
        Multi mul = new Multi();
        Div div = new Div();

        System.out.println("Sum: " + add.sum(a, b));
        System.out.println("Difference: " + sub.diff(a, b));
        System.out.println("Product: " + mul.product(a, b));
        System.out.println("Quotient: " + div.divide(a, b));
    }
}