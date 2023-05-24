package view.Login;

import java.util.Scanner;

import common.ErrorMessage;
import common.ErrorState;
import controller.TeacherController;

public class TeacherLogin {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void teacherLogin(Scanner scanner) {
        TeacherController teacherController = TeacherController.getInstance();

        System.out.print("Enter your email: ");
        String email = scanner.next();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        teacherController.login(email, password);

        ErrorState errorState = ErrorState.getInstance();
        ErrorMessage errorMessage = errorState.getErrorMessage();

        if (errorMessage != null) {
            System.out.println(ANSI_RED + errorMessage.getErrorMessage() + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Login successful." + ANSI_RESET);
    }
}
