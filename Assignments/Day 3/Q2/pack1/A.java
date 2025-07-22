package pack1;

public class A {
    private int pri = 1;
    int def = 2;
    protected int pro = 3;
    public int pub = 4;

    public void display() {
        System.out.println("Private: " + pri);
        System.out.println("Default: " + def);
        System.out.println("Protected: " + pro);
        System.out.println("Public: " + pub);
    }
}