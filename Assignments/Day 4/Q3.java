public class Q3 {

    private int outerVar = 100;

    class Inner {
        void show() {
            System.out.println("Accessing outer class private variable: " + outerVar);
        }
    }

    public static void main(String[] args) {
        Q3 outer = new Q3();
        Q3.Inner inner = outer.new Inner();
        inner.show();
    }
}