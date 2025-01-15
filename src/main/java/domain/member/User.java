package domain.member;

import java.util.Date;

public class User {
    private int id;
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String nickName;
    private Date birthDate;
    private String gender;
    private boolean isAdmin;
    private Date createdAt;
    private Date deletedAt;
    private boolean isDeleted;
    private boolean agree;
    private int score;

    public User() {}
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User(int id, String userId, String userName, String email, String password, String nickName,
                Date birthDate, String gender, boolean isAdmin, Date createdAt, Date deletedAt, boolean isDeleted, boolean agree) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
        this.agree = agree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}
