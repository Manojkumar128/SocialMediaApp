package services;
import db.DBConnection;
import java.sql.*;

public class CommentService {
    public void addComment(int userId, int postId, String comment) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO comments(user_id, post_id, comment) VALUES(?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setInt(2, postId);
            stmt.setString(3, comment);
            stmt.executeUpdate();
            System.out.println("💬 Comment added!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public void viewComments(int postId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT users.username, comments.comment, comments.timestamp " +
                "FROM comments JOIN users ON comments.user_id=users.id " +
                "WHERE post_id=? ORDER BY comments.timestamp ASC");
            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n--- Comments ---");
            while (rs.next()) {
                System.out.println(rs.getString("username") + ": " +
                                   rs.getString("comment") + " (" +
                                   rs.getTimestamp("timestamp") + ")");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
