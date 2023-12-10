package com.example.filemanagement;

import com.example.filemanagement.model.User;
import com.example.filemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option: \n1. Register User\n2. Login User\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void registerUser(Scanner scanner) {
        System.out.println("Registering new user...");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Remember, in a real app, you should hash the password

        try {
            userService.createUser(user);
            System.out.println("User registered successfully!");
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void loginUser(Scanner scanner) {
        System.out.println("Logging in...");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = userService.authenticateUser(username, password);
        if (isAuthenticated) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed: Invalid credentials.");
        }
    }
}
