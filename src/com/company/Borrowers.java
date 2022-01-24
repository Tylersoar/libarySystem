package com.company;

public class Borrowers extends Main {
    protected static String email;
    protected static String password;

    @Override
    public String toString() {
        return "email" + email +
                "password" + password;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Borrowers.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Borrowers.password = password;
    }
}


