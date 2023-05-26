package view.Register;

import java.util.Scanner;

import controller.StudentController;
import exceptions.EmailAlreadyUser;
import exceptions.GrupaNotFoundException;
import model.entity.Student;

public class StudentRegister {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void studentRegistration(Scanner scanner) {
        StudentController studentController = StudentController.getInstance();

        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        System.out.print("Enter your last name: ");
        String lastName = scanner.next();

        System.out.print("Enter your email: ");
        String email = scanner.next();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        System.out.print("Enter your class: ");
        String className = scanner.next();

        try {
            // Call the createAccount() function to create a student account
            Student user = studentController.createAccount(firstName, lastName, email, password, className);
            System.out.println(ANSI_GREEN + "Registration successful. Welcome, " + lastName + "!" + ANSI_RESET);
            view.StudentMenu.StudentMenu.showMenu(scanner, user);
        } catch (EmailAlreadyUser e) {
            System.out.println(ANSI_RED + "\nRegistration failed. Email already used.\n" + ANSI_RESET);
        } catch (GrupaNotFoundException e) {
            System.out.println(ANSI_RED + "\nRegistration failed Class not found.\n" + ANSI_RESET);

            // TODO: Afiseaza clasaele existente
        }
    }
}
    