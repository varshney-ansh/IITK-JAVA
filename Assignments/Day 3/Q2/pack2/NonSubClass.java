// File: NonSubClass.java
package pack2;
import pack1.A;

public class NonSubClass {
    public static void main(String[] args) {
        A obj = new A();
        System.out.println("Public: " + obj.pub);
    }
}