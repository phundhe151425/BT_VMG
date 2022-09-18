package com.example.buoi9_th1_app_user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class EncrytedPasswordUtils {
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String password = in.nextLine();
        String encrytedPassword = encrytePassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);
    }
}
