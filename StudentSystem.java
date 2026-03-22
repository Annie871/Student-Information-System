import java.sql.*;
import java.util.Scanner;

public class StudentSystem {
    // 1. Database Credentials
    static final String URL = "jdbc:mysql://localhost:3306/student_db";
    static final String USER = "root";
    static final String PASS = "root"; // <--- UPDATE THIS!

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n=== STUDENT INFORMATION SYSTEM ===");
                System.out.println("1. Admit New Student");
                System.out.println("2. View All Students");
                System.out.println("3. Remove a Student");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // Clear the scanner buffer

                if (choice == 4) {
                    System.out.println("Shutting down system. Goodbye!");
                    break;
                }

                try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Roll Number: ");
                            String roll = sc.nextLine();
                            System.out.print("Enter Full Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Course (e.g., Computer Science): ");
                            String course = sc.nextLine();
                            System.out.print("Enter Age: ");
                            int age = sc.nextInt();
                            addStudent(conn, roll, name, course, age);
                            break;
                        case 2:
                            viewStudents(conn);
                            break;
                        case 3:
                            System.out.print("Enter the Database ID of the student to remove: ");
                            int id = sc.nextInt();
                            deleteStudent(conn, id);
                            break;
                        default:
                            System.out.println("[ERROR] Invalid menu choice.");
                    }
                } catch (SQLException e) {
                    System.out.println("[DATABASE ERROR] " + e.getMessage());
                }
            }
        } finally {
            sc.close();
        }
    }

    // METHOD 1: Add Student
    public static void addStudent(Connection conn, String roll, String name, String course, int age)
            throws SQLException {
        String sql = "INSERT INTO students (roll_number, full_name, course, age) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, roll);
        pstmt.setString(2, name);
        pstmt.setString(3, course);
        pstmt.setInt(4, age);

        pstmt.executeUpdate();
        System.out.println("[SUCCESS] Student admitted to the system!");
    }

    // METHOD 2: View All Students
    public static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students ORDER BY roll_number ASC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- REGISTERED STUDENTS ---");
        System.out.printf("%-5s | %-12s | %-20s | %-20s | %-5s\n", "ID", "ROLL NO", "NAME", "COURSE", "AGE");
        System.out.println("-------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-5d | %-12s | %-20s | %-20s | %-5d\n",
                    rs.getInt("id"),
                    rs.getString("roll_number"),
                    rs.getString("full_name"),
                    rs.getString("course"),
                    rs.getInt("age"));
        }
    }

    // METHOD 3: Delete Student
    public static void deleteStudent(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("[SUCCESS] Student record removed.");
        } else {
            System.out.println("[ERROR] No student found with that ID.");
        }
    }
}