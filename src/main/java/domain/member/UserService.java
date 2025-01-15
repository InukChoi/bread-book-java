package domain.member;

import global.common.DbConnection;

public class UserService {
    private UserDao userDao;
    public UserService() {this.userDao = new UserDao(DbConnection.getConnection());}
    public void signup(User user) {
        String originPassword = user.getPassword();

        user.setPassword("암호화됨" + originPassword);

        userDao.insertUser(user);
    }
}
