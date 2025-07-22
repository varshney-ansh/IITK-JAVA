public class Q2 {

    static class StaticClass {
        static int staticVar = 10;
        int nonStaticVar = 20;

        static void staticMethod() {
            System.out.println("Inside static method of StaticClass");
            System.out.println("staticVar = " + staticVar);
        }

        void nonStaticMethod() {
            System.out.println("Inside non-static method of StaticClass");
            System.out.println("staticVar = " + staticVar);
            System.out.println("nonStaticVar = " + nonStaticVar);
        }
    }

    public static void main(String[] args) {
        StaticClass.staticMethod();
        System.out.println("Accessing staticVar directly: " + StaticClass.staticVar);

        StaticClass obj = new StaticClass();
        obj.nonStaticMethod();
        System.out.println("Accessing nonStaticVar via object: " + obj.nonStaticVar);
    }
}