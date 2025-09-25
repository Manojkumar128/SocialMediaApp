package services;
import db.DBConnection;
import java.sql.*;

public class LikeService {
    public void likePost(int userId, int postId) {
        try (Connection con = DBConnection.getConnection()) {
            // Check if already liked
            PreparedStatement check = con.prepareStatement(
                "SELECT * FROM likes WHERE user_id=? AND post_id=?");
            check.setInt(1, userId);
            check.setInt(2, postId);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                System.out.println("❌ You already liked this post!");
                return;
            }

            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO likes(user_id, post_id) VALUES(?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, postId);
            stmt.executeUpdate();
            System.out.println("👍 Post liked!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
