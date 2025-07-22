
public class Q4 {

    void display() {
        class MethodLocalInner {
            void show() {
                System.out.println("Inside method local inner class");
            }
        }

        MethodLocalInner obj = new MethodLocalInner();
        obj.show();
    }

    public static void main(String[] args) {
        Q4 outer = new Q4();
        outer.display();
    }
}