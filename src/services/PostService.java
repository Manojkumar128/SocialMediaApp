package services;
import db.DBConnection;
import java.sql.*;

public class PostService {
    public void createPost(int userId, String content) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO posts(user_id, content) VALUES(?, ?)");
            stmt.setInt(1, userId);
            stmt.setString(2, content);
            stmt.executeUpdate();
            System.out.println("✅ Post created successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void viewPosts() {
        try (Connection con = DBConnection.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT posts.id, users.username, posts.content, posts.timestamp " +
                "FROM posts JOIN users ON posts.user_id=users.id ORDER BY posts.timestamp DESC");
            System.out.println("\n=== All Posts ===");
            while (rs.next()) {
                System.out.println("Post ID: " + rs.getInt("id"));
                System.out.println("User: " + rs.getString("username"));
                System.out.println("Content: " + rs.getString("content"));
                System.out.println("Time: " + rs.getTimestamp("timestamp"));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
