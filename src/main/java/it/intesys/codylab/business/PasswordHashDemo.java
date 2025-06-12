package it.intesys.codylab.business;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashDemo {
    public static void main(String[] args) {
        String rawPassword = "Talent2025";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Password originale: " + rawPassword);
        System.out.println("Password hashata: " + hashedPassword);
    }
}