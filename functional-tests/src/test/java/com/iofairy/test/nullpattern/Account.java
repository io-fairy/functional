package com.iofairy.test.nullpattern;

/**
 * @author GG
 * @version 1.0
 */
public class Account {
    public String id;
    public String userName;
    public String password;

    public Account(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
