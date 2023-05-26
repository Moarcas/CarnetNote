package view.Register;

import java.util.Scanner;

import controller.ProfesorController;
import exceptions.EmailAlreadyUser;
import model.entity.Profesor;

public class TeacherRegister {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void teacherRegistration(Scanner scanner) {
        ProfesorController teacherController = ProfesorController.getInstance();

        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        System.out.print("Enter your last name: ");
        String lastName = scanner.next();

        System.out.print("Enter your email: ");
        String email = scanner.next();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        try {
            // Call the createAccount() function to create a teacher account
            Profesor user = teacherController.createAccount(firstName, lastName, email, password);
            System.out.println(ANSI_GREEN + "Registration successful. Welcome, " + lastName + "!" + ANSI_RESET);
            view.TeacherMenu.TeacherMenu.showMenu(scanner, user);
        } catch (EmailAlreadyUser e) {
            System.out.println(ANSI_RED + "\nRegistration failed. Email already used.\n" + ANSI_RESET);
        }

    }
}
