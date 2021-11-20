package com.iofairy.test.nullpattern;

import com.iofairy.test.nullpattern.Account;

/**
 * @author GG
 * @version 1.0
 */
public class User {
    public String name;
    public int age;
    public Account account;

    public User(String name, int age, Account account) {
        this.name = name;
        this.age = age;
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }
}
