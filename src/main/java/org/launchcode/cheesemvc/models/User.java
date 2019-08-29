package org.launchcode.cheesemvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class User {

    @NotNull
    @Size(min=5, max=15, message = "Username must be between 5 and 15")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min=6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;
    private int userId;
    private LocalDate joinDate = LocalDate.now();
    private static int nextId=1;

    public User() {
        userId = nextId;
        nextId++;
    }

    public User(String username, String email, String password, String verifyPassword) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public void checkPassword() {
        if (this.password != null && this.verifyPassword != null && !this.password.equals(this.verifyPassword)) {
            this.verifyPassword = null;
        }
    }
}
