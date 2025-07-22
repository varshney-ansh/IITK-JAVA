public class Q5 {

    void display() {
        System.out.println("Display method of Q5");
    }

    public static void main(String[] args) {
        Q5 obj = new Q5() {
            void display() {
                System.out.println("Display method of anonymous subclass");
            }
        };
        obj.display();
    }
}