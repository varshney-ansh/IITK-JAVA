package mypack;

class Balance {
    String name;
    double balance;

    Balance(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void show() {
        System.out.println("Name: " + name + ", Balance: " + balance);
    }
}

public class AcctBalance {
    public static void main(String[] args) {
        Balance b = new Balance("Ansh", 5000.00);
        b.show();
    }
}