package domain.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO member (userid, username, email, password, nickName, birth_date, gender, agree) " +
                "VALUE (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getNickName());
            pstmt.setDate(6, new Date(user.getBirthDate().getTime()));
            pstmt.setString(7, user.getGender());
            pstmt.setBoolean(8, user.isAgree());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
