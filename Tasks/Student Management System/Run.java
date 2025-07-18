import java.util.*;
import java.sql.*;
import java.io.*;

class Account {
    public static void ViewProfile(Connection con, String username) {
        System.out.println("+-------------------------+");
        String query = "SELECT * FROM students WHERE stu_username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("stu_name"));
                System.out.println("Email: " + rs.getString("stu_username"));
                System.out.println("Class: " + rs.getString("stu_class"));
                System.out.println("Year: " + rs.getString("stu_year"));
                System.out.println("Mobile: " + rs.getString("stu_mobile"));
                System.out.println("Address: " + rs.getString("stu_address"));
                System.out.println("+-------------------------+");
            } else {
                System.out.println("Student Not Found.");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    public static void ViewAllStudents(Connection con) {
        System.out.println("+-------------------------+");
        // Logic to fetch and display
        String query = "SELECT * FROM students";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("stu_name"));
                System.out.println("Email: " + rs.getString("stu_username"));
                System.out.println("Class: " + rs.getString("stu_class"));
                System.out.println("Year: " + rs.getString("stu_year"));
                System.out.println("Mobile: " + rs.getString("stu_mobile"));
                System.out.println("Address: " + rs.getString("stu_address"));
                System.out.println("+-------------------------+");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    public static void RemoveStudent(Connection con) {
        System.out.println("+-------------------------+");
        System.out.println("Enter Student ID to Remove: ");
        Scanner cs = new Scanner(System.in);
        int studentId = cs.nextInt();

        String query = "DELETE FROM students WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Removed Successfully.");
            } else {
                System.out.println("Student Not Found.");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    public static void UpdateStudent(Connection con) {
        System.out.println("+-------------------------+");
        System.out.println("Enter Student ID to Update: ");
        Scanner cs = new Scanner(System.in);
        int studentId = cs.nextInt();

        final String[] fields = {
                "name", "username", "password", "class", "year", "mobile", "address"
        };

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter field to update (name, username, pass, class, year, mobile, address): ");
        String field = sc.nextLine();

        if (!Arrays.asList(fields).contains(field)) {
            System.out.println("Error: Invalid field.");
            return;
        }

        System.out.println("Enter new value for " + field + ": ");
        String newValue = sc.nextLine();

        String query = "UPDATE students SET " + "stu_" + field + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newValue);
            ps.setInt(2, studentId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Updated Successfully.");
            } else {
                System.out.println("Student Not Found.");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    public static boolean AdminMenu(Connection con) {
        System.out.println("+-------------------------+");
        System.out.println("1 - View All Students");
        System.out.println("2 - Add Student");
        System.out.println("3 - Remove Student");
        System.out.println("4 - Update Student");
        System.out.println("5 - Logout");
        Scanner cs = new Scanner(System.in);
        int option = cs.nextInt();
        switch (option) {
            case 1:
                ViewAllStudents(con);
                break;
            case 2:
                Run.AddStudent(con);
                break;
            case 3:
                RemoveStudent(con);
                break;
            case 4:
                UpdateStudent(con);
                break;
            case 5:
                System.out.println("Logging out...");
                return false; // Exit the admin menu
            default:
                System.out.println("Error: Invalid Option! Please Choose Correct One.");
                break;
        }
        return true;
    }
}

class Run {
    public static void WelcomeMessage() {
        System.out.println("+-------------------------+");
        File f = new File("logo.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            System.out.println("Student Management System");
            System.out.println("  -- Made by Ansh Varshney (*_*)");
        } catch (FileNotFoundException e) {
            System.err.println("Logo file not found: " + e.getMessage());
        }
        System.out.println("+-------------------------+");
    }

    static Connection DbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "root", "");
            return con;
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return null;
        }
    }

    static boolean StartMenu(Connection con) {
        System.out.println("1 - Admin Login");
        System.out.println("2 - Student Login");
        System.out.println("3 - Register Student/Admin");
        System.out.println("4 - Exit");
        Scanner cs = new Scanner(System.in);
        int option = cs.nextInt();

        switch (option) {
            case 1:
                AdminMenu(con);
                break;

            case 2:
                StudentMenu(con);
                break;

            case 3:
                AddStudentOrAdmin(con);
                break;

            case 4:
                return false;

            default:
                System.out.println("Error: Invalid Option! Please Choose Correct One.");
                break;
        }

        return true;

    }

    static void StudentMenu(Connection con) {
        System.out.println("+-------------------------+");
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter Your Username: ");
        String user = cs.next();
        System.out.println("Enter Your Password: ");
        String pass = cs.next();

        try {
            String u = "SELECT * FROM students WHERE stu_username=? AND stu_pass = ?";
            PreparedStatement pst = con.prepareStatement(u);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet i = pst.executeQuery();
            if (i.next()) {
                System.out.println("Login Successfull.");
                System.out.println("+-------------------------+");
                boolean continue_operation = true;
                while (continue_operation) {
                    System.out.println("1 - View Profile");
                    System.out.println("3 - Logout");
                    int choice = cs.nextInt();
                    switch (choice) {
                        case 1:
                            Account.ViewProfile(con, user);
                            break;
                        case 3:
                            continue_operation = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("+-------------------------+");
                System.out.println("Invalid Username and Password");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }

    }

    static void AdminMenu(Connection con) {
        System.out.println("+-------------------------+");
        Scanner cs = new Scanner(System.in);
        System.out.println("Enter Your Username: ");
        String user = cs.next();
        System.out.println("Enter Your Password: ");
        String pass = cs.next();

        try {
            String u = "SELECT * FROM admins WHERE ad_username=? AND ad_pass = ?";
            PreparedStatement pst = con.prepareStatement(u);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet i = pst.executeQuery();
            if (i.next()) {
                System.out.println("Login Successfull.");
                boolean continue_operation = true;
                while (continue_operation) {
                    continue_operation = Account.AdminMenu(con);
                }
            } else {
                System.out.println("+-------------------------+");
                System.out.println("Invalid Username and Password");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }

    }

    static void AddStudent(Connection con) {
        System.out.println("+-------------------------+");
        System.out.println("Student Name: ");
        Scanner sc = new Scanner(System.in);
        String stu_name = sc.nextLine();
        System.out.println("Student Username: ");

        String stu_user = sc.next();
        System.out.println("Student Password: ");
        String stu_pass = sc.next();
        System.out.println("Student Class (CSE/ELEX):  ");
        String stu_class = sc.next();
        System.out.println("Student Year (1/2/3/4): ");
        int stu_year = sc.nextInt();
        System.out.println("Student Phone Number: ");
        Long stu_mobile = sc.nextLong();
        System.out.println("Student Address");
        String stu_address = sc.nextLine();

        try {
            String u = "INSERT INTO students (stu_name, stu_username, stu_pass, stu_class, stu_year, stu_mobile, stu_address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(u);
            pstmt.setString(1, stu_name);
            pstmt.setString(2, stu_user);
            pstmt.setString(3, stu_pass);
            pstmt.setString(4, stu_class);
            pstmt.setLong(6, stu_mobile);
            pstmt.setInt(5, stu_year);
            pstmt.setString(7, stu_address);

            int res = pstmt.executeUpdate();
            if (res > 0)
                System.out.println("Registered Successfully !!");
            else
                System.out.println("Failed! Try again later..");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
        }
    }

    static void AddStudentOrAdmin(Connection con) {
        System.out.println("+-------------------------+");
        System.out.println("1 - Admin");
        System.out.println("2 - Student");
        Scanner cs = new Scanner(System.in);
        int option = cs.nextInt();

        switch (option) {
            case 1:
                System.out.println("+-------------------------+");
                System.out.println("Admin Name: ");
                Scanner sc = new Scanner(System.in);
                String ad_name = sc.nextLine();
                System.out.println("Admin Username: ");

                String ad_user = sc.next();
                System.out.println("Admin Password: ");
                String ad_pass = sc.next();
                System.out.println("Admin Type (Prof/HOD/Director): ");
                String ad_type = sc.next();
                System.out.println("Admin Phone Number: ");
                Long ad_mobile = sc.nextLong();
                try {
                    String u = "INSERT INTO admins (ad_name, ad_username, ad_pass, ad_type, ad_mobile) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(u);
                    pstmt.setString(1, ad_name);
                    pstmt.setString(2, ad_user);
                    pstmt.setString(3, ad_pass);
                    pstmt.setString(4, ad_type);
                    pstmt.setLong(5, ad_mobile);

                    int res = pstmt.executeUpdate();
                    if (res > 0)
                        System.out.println("Registered Successfully !!");
                    else
                        System.out.println("Failed! Try again later..");
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Error: " + e);
                }

                break;

            case 2:
                AddStudent(con);
                break;

            default:
                System.out.println("Error: Invalid Option! Please Choose Correct One.");
                break;
        }

    }

    public static void main(String... k) {
        Connection con = DbConnect();
        if (con == null) {
            System.out.println("Error: Database not connected!");
            System.exit(0);
        }
        while (true) {
            WelcomeMessage();
            boolean continue_operation = StartMenu(con);
            if (!continue_operation)
                break;
        }
    }
}
