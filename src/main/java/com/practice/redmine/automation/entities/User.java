package com.practice.redmine.automation.entities;

public class User {

    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String language;

    public User(String username, String password, String firstName, String lastName, String email, String language) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.language = language;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
