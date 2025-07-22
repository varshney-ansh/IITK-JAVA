// File: SubClass.java
package pack2;
import pack1.A;

public class SubClass extends A {
    public static void main(String[] args) {
        SubClass obj = new SubClass();
        System.out.println("Protected: " + obj.pro);
        System.out.println("Public: " + obj.pub);
    }
}