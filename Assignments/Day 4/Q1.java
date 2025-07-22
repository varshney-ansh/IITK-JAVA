public class Q1 {
    int outerVar = 10;
    static int staticOuterVar = 20;

    static class Nested {
        void display() {
            // System.out.println("Outer variable: " + outerVar); // Not accessible
            System.out.println("Static outer variable: " + staticOuterVar);
        }
    }

    public static void main(String[] args) {
        Nested n = new Nested();
        n.display();
    }
}
