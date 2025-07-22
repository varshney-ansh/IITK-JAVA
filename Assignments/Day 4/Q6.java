
public class Q6 {

    interface Message {
        void show();
    }

    public static void main(String[] args) {
        Message obj = new Message() {
            public void show() {
                System.out.println("Anonymous implementer of interface");
            }
        };
        obj.show();
    }
}