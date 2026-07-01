package com.tailorsin.model;

public class User {
    private String email;
    private String phone;
    private String password;
    private String role;

    public User() {
    }

    public User(String email, String phone, String password, String role) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean matchesIdentity(String identity) {
        return identity != null && (identity.equalsIgnoreCase(email) || identity.equals(phone));
    }
}
