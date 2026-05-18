import java.sql.*;

public class InvoiceDatabase {
    private String url = "jdbc:mysql://localhost:3306/javadb";
    private String dbUser = "root";
    private String dbPassword = "";
    
    public boolean Add_Invoice(String invoiceNumber, String customerName, int amount, int payment) {
        String insertQuery = "INSERT INTO receivable (invno, customer, amount, payment) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(insertQuery)) {

            pst.setString(1, invoiceNumber);
            pst.setString(2, customerName);
            pst.setInt(3, amount);
            pst.setInt(4, payment);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Display_Invoice() {
        String query = "SELECT invno, customer, amount, payment FROM receivable WHERE amount > payment";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            int ctr = 0;
            System.out.println();
            System.out.println("--- Invoices with Balance ---");
            while (rs.next()) {
                if (rs.getInt("amount") > rs.getInt("payment")) {   
                    String invno = rs.getString("invno");
                    String customer = rs.getString("customer");
                    int amount = rs.getInt("amount") - rs.getInt("payment");
                    System.out.println("Invoice: " + invno + " | " + "Customer: " + customer + " | " + "Balance: " + amount);
                    ctr++;
                }
            }
            if (ctr == 0) {
                System.out.println("==================================");
                System.out.println("| *          *  ALL          *   |");
                System.out.println("|             INVOICES           |");
                System.out.println("|    *          ARE     *        |");
                System.out.println("|          *   FULLY        *    |");
                System.out.println("|      *       PAID!          *  |");
                System.out.println("==================================");
            } 
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Pay_Invoice(int payment, String invno) {
        String updateQuery = "UPDATE receivable SET payment = payment +? WHERE invno = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, payment);
            pst.setString(2, invno);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean Delete_Invoice(String invno){
        String deleteQuery = "DELETE FROM receivable WHERE invno = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(deleteQuery)) {

            pst.setString(1, invno);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
