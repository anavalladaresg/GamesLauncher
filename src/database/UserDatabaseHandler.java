package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseHandler extends DatabaseHandler {

    public UserDatabaseHandler() {
        super();
    }

    public void addUser(String userName, String password) {
        String SQL = "INSERT INTO users(userName, password) VALUES(?,?)";
        String regex = "^[a-zA-Z0-9]+$";

        if (!userName.matches(regex) || !password.matches(regex)) {
            System.out.println("Username and password can only contain alphanumeric characters.");
            return;
        }

        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean userExists(String userName) {
        String SQL = "SELECT * FROM users WHERE userName = ?";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userName);
            return pstmt.executeQuery().next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getPassword(String userName) {
        String SQL = "SELECT password FROM users WHERE userName = ?";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
