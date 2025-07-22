public class Q7 {

    class Info {
        class Company {
            void show() {
                System.out.println("Inner class Company inside non-static class Info");
            }
        }
    }

    public static void main(String[] args) {
        Q7 outer = new Q7();
        Q7.Info info = outer.new Info();
        Q7.Info.Company company = info.new Company();
        company.show();
    }
}