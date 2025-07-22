package pack;

class A {
    private int pri = 1;
    int def = 2;
    protected int pro = 3;
    public int pub = 4;

    void display() {
        System.out.println("Private: " + pri);
        System.out.println("Default: " + def);
        System.out.println("Protected: " + pro);
        System.out.println("Public: " + pub);
    }
}

class SubClass extends A {
    void show() {
        System.out.println("Default: " + def);
        System.out.println("Protected: " + pro);
        System.out.println("Public: " + pub);
    }
}

class OtherClass {
    void show() {
        A obj = new A();
        System.out.println("Default: " + obj.def);
        System.out.println("Protected: " + obj.pro);
        System.out.println("Public: " + obj.pub);
    }
}

public class Q3 {
    public static void main(String[] args) {
        A a = new A();
        a.display();

        SubClass s = new SubClass();
        s.show();

        OtherClass o = new OtherClass();
        o.show();
    }
}