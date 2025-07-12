import java.util.*;
import java.sql.*;
import java.io.*;

class Account {
   public static boolean WriteTransactionHistory(Connection con, long accNo, String transactionType, double amount) {
        try {
            String q = "INSERT INTO transactions (acc_no, amount, type) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setLong(1, accNo);
            pstmt.setString(3, transactionType);
            pstmt.setDouble(2, amount);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if the transaction was recorded successfully
        } catch (Exception e) {
            System.err.println("Error writing transaction history: " + e.getMessage());
            return false; // Return false if there was an error
        }
    }

    public static void SeeTransactionHistory(Connection con, long accNo) {
        try {
            String q = "SELECT * FROM transactions WHERE acc_no = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setLong(1, accNo);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("+-------------------------+");
            System.out.println("Transaction History:");
            System.out.println("+-------------------------+");
            while (rs.next()) {
                System.out.println("Transaction ID: " + rs.getInt("tr_id"));
                System.out.println("Type: " + rs.getString("type"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("+-------------------------+");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving transaction history: " + e.getMessage());
        }
    }

    public static void DepositMoney(Connection con, long accNo, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        try {
            String q = "UPDATE users SET balance = balance + ? WHERE acc_no = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setDouble(1, amount);
            pstmt.setLong(2, accNo);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Write transaction history
                boolean transactionRecorded = WriteTransactionHistory(con, accNo, "Credit", amount);
                if (transactionRecorded) {
                    System.out.println("+-------------------------+");
                    System.out.println("Deposit successful! New balance updated.");
                    System.out.println("+-------------------------+");
                } else {
                    System.out.println("Error: Failed to record transaction history.");
                }
            } else {
                System.out.println("Error: Deposit failed. Account not found.");
            }
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
        }
    }

    public static void WithdrawMoney(Connection con, long accNo, double amount) {
        if( amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        try{
            String available_balance = "SELECT balance FROM users WHERE acc_no = ?";
            PreparedStatement pstmt = con.prepareStatement(available_balance);
            pstmt.setLong(1, accNo);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                double currentBalance = rs.getDouble("balance");
                if(currentBalance >= amount) {
                    String q = "UPDATE users SET balance = balance - ? WHERE acc_no = ?";
                    PreparedStatement updatePstmt = con.prepareStatement(q);
                    updatePstmt.setDouble(1, amount);
                    updatePstmt.setLong(2, accNo);
                    int rowsAffected = updatePstmt.executeUpdate();
                    if(rowsAffected > 0) {
                        // Write transaction history
                        boolean transactionRecorded = WriteTransactionHistory(con, accNo, "Debit", amount);
                        if (transactionRecorded) {
                            System.out.println("+-------------------------+");
                            System.out.println("Withdrawal successful! New balance updated.");
                            System.out.println("+-------------------------+");
                        } else {
                            System.out.println("Error: Failed to record transaction history.");
                        }
                    } else {
                        System.out.println("Withdrawal failed. Account not found.");
                    }
                } else {
                    System.out.println("Insufficient balance for withdrawal.");
                }
            } else {
                System.out.println("Account not found.");
            }

        }catch(Exception exception){
            System.err.println("Oops! there are errors. Error: " + exception);
            return;
        }

    }

    public static void CheckBalance(Connection con, long accNo) {
        try {
            String q = "SELECT balance FROM users WHERE acc_no = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setLong(1, accNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                System.out.println("+-------------------------+");
                System.out.println("Your current balance is: " + balance);
                System.out.println("+-------------------------+");
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
        }
    }

    public static void SeeYourProfile(Connection con, long accNo) {
        try {
            String q = "SELECT * FROM users WHERE acc_no = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setLong(1, accNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("+-------------------------+");
                System.out.println("Account No: " + rs.getLong("acc_no"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Pin Code: " + rs.getInt("pin_code"));
                System.out.println("Phone No: " + rs.getLong("phone_no"));
                System.out.println("Balance: " + rs.getDouble("balance"));
                System.out.println("Account Type: " + rs.getString("acc_type"));
                System.out.println("+-------------------------+");
            } else {
                System.out.println("Account not found.");
            }
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
        }
    }

    public static long GetAccountNumber(Connection con, String username) {
        try {
            String q = "SELECT acc_no FROM users WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("acc_no");
            } else {
                System.out.println("Account not found for the given username.");
                return -1; // Indicating account not found
            }
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
            return -1; // Indicating error
        }
    }

    public static boolean IsUsernameAvailable(Connection con, String username) {
        try {
            String q = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return !rs.next(); // If no record found, username is available
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
            return false; // Indicating error
        }
    }

    public static void CreateAccount(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Your Name: ");
        String name = sc.nextLine();
        System.out.println("Your Address: ");
        String address = sc.nextLine();
        System.out.println("Pin Code: ");
        int pin = sc.nextInt();
        System.out.println("Your Phone Number: ");
        Long phone = sc.nextLong();
        sc.nextLine(); // Consume the leftover newline character
        System.out.println("Your desired username:");
        String username = sc.nextLine();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again.");
            return;
        }

        if (!IsUsernameAvailable(con, username)) {
            System.out.println("Username already exists. Please choose a different username.");
            username = sc.nextLine();
        }

        System.out.println("Your desired password:");
        String password = sc.nextLine();

        try {
            String q = "INSERT INTO users (name, address, pin_code, acc_type, username, pass, balance, phone_no) VALUES (?, ?, ?, 'savings', ?, ?, 0, ?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setInt(3, pin);
            pstmt.setString(4, username);
            pstmt.setString(5, password);
            pstmt.setLong(6, phone);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                String getQuery = "SELECT * FROM users WHERE username = ? ";
                PreparedStatement getPstmt = con.prepareStatement(getQuery);
                getPstmt.setString(1, username);
                ResultSet rs = getPstmt.executeQuery();
                System.out.println("+-------------------------+");
                System.out.println("Account created successfully!");
                while (rs.next()) {
                    System.out.println("Your Account No: " + rs.getLong("acc_no"));
                }
                System.out.println("+-------------------------+");
            } else {
                System.out.println("Failed to create account.");
            }
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
        }

    }

    public static long LoginAccount(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        try {
            String q = "SELECT * FROM users WHERE username = ? AND pass = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful!");
                long accNo = rs.getLong("acc_no");
                return accNo; // Return the account number for further operations
            } else {
                System.out.println("Invalid username or password.");
                return -1; // Indicating login failure
            }
        } catch (SQLException e) {
            System.err.println("Oops! there are errors. Error: " + e);
            return -1; // Indicating error
        }
    }
}

class Run {
    static boolean AccountOperationsMenu(Connection con, long acc_no) {
        System.out.println("+-------------------------+");
        System.out.println("Account Operations Menu");
        System.out.println("+-------------------------+");
        System.out.println("1 - Deposit Money");
        System.out.println("2 - Withdraw Money");
        System.out.println("3 - Check Balance");
        System.out.println("4 - See Your Profile");
        System.out.println("5 - See Transaction History");
        System.out.println("6 - Exit/Logout");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                // Call deposit method
                System.out.println("Enter amount to deposit: ");
                double depositAmount = sc.nextDouble();
                Account.DepositMoney(con, acc_no, depositAmount);
                break;
            case 2:
                // Call withdraw method
                System.out.println("Enter amount to withdraw: ");
                double withdrawAmount = sc.nextDouble();
                Account.WithdrawMoney(con, acc_no, withdrawAmount);
                break;
            case 3:
                // Call check balance method
                Account.CheckBalance(con, acc_no);
                break;
            case 4:
                Account.SeeYourProfile(con, acc_no);
                break;

            case 5:
               Account.SeeTransactionHistory(con, acc_no);
                break;

            case 6:
                System.out.println("Logging out ...");
                return false; // Exit the menu
            default:
                System.err.println("Invalid choice. Please try again.");
        }
        return true; // Continue the menu
    }

    static Connection DbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbibank", "root", "Kumar9090@");
            return con;
        } catch (Exception e) {
            System.err.println("Oops! there are errors. Error: " + e);
            return null;
        }
    }

    static boolean AccountLoginRegPopup(Connection con) {
        System.out.println("1 - Create Account");
        System.out.println("2 - Login Account ");
        System.out.println("3 - Exit");
        Scanner cs = new Scanner(System.in);
        int user_option = cs.nextInt();

        switch (user_option) {
            case 1:
                System.out.println("+-------------------------+");
                System.out.println("Create Your Account");
                System.out.println("+-------------------------+");
                Account.CreateAccount(con);
                break;
            case 2:
                System.out.println("+-------------------------+");
                System.out.println("Log into Your Account");
                System.out.println("+-------------------------+");
                long acc_no = Account.LoginAccount(con);
                if (acc_no != -1) {
                    boolean continueOperations = true;
                    while (continueOperations) {
                        continueOperations = AccountOperationsMenu(con, acc_no);
                    }
                } else {
                    System.out.println("Login failed. Please try again.");
                }
                break;
            case 3:
                System.out.println("+-------------------------+");
                System.out.println("Thanks for banking with us!");
                System.out.println("+-------------------------+");
                // break;return;
                return false;
            default:
                System.err.println("Oops! Invalid option");
        }

        return true;
    }

    public static void WelcomeMessage() {
        System.out.println("+-------------------------+");
        File f = new File("logo.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            System.out.println("-- Made by Ansh Varshney (*_*)");
        } catch (FileNotFoundException e) {
            System.err.println("Logo file not found: " + e.getMessage());
        }
        System.out.println("+-------------------------+");
    }

    public static void main(String... k) {
        while (true) {
            Connection con = DbConnect();
            if (con == null) {
                break;
            }

            WelcomeMessage();
            boolean ShouldContinue = AccountLoginRegPopup(con);
            if (!ShouldContinue)
                break;
        }
    }
}