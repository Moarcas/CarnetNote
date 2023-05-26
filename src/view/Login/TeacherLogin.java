package view.Login;

import java.util.Scanner;

import controller.ProfesorController;
import model.entity.Profesor;

public class TeacherLogin {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void teacherLogin(Scanner scanner) {
        ProfesorController teacherController = ProfesorController.getInstance();

        System.out.print("Enter your email: ");
        String email = scanner.next();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        Profesor user = teacherController.login(email, password);

        if (user == null) {
            System.out.println(ANSI_RED + "\nEmail or password incorrect\n" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "\nLogin successful.\n" + ANSI_RESET);
        view.TeacherMenu.TeacherMenu.showMenu(scanner, user);
    }
}
