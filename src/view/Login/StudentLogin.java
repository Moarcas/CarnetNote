package view.Login;

import java.util.Scanner;

import controller.StudentController;
import model.entity.Student;

public class StudentLogin {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void studentLogin(Scanner scanner) {
        StudentController studentController = StudentController.getInstance();

        System.out.print("Enter your email: ");
        String email = scanner.next();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        Student user = studentController.login(email, password);

        System.out.println(user);

        if (user == null) {
            System.out.println(ANSI_RED + "\nEmail or password incorrect\n" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "\nLogin successful.\n" + ANSI_RESET);
        view.StudentMenu.StudentMenu.showMenu(scanner, user);
    }
}
