package services;
import db.DBConnection;
import java.sql.*;

public class UserService {
    public void registerUser(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO users(username, password) VALUES(?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("✅ User registered successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public int loginUser(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login successful!");
                return rs.getInt("id");
            } else {
                System.out.println("❌ Invalid credentials!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        return -1;
    }
}
