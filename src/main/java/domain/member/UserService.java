package domain.member;

import global.common.HikariCp;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao;
    public UserService() {
        try {
            this.userDao = new UserDao(HikariCp.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void signup(User user) {
        String originPassword = user.getPassword();

        user.setPassword("암호화됨" + originPassword);

        userDao.insertUser(user);
    }

    public int login(User user) {
        String originPassword = user.getPassword();

        user.setPassword("암호화됨" + originPassword);

        int userIdx = userDao.login(user);

        return userIdx;
    }
}
