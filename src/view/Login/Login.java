package view.Login;

import java.util.Scanner;

public class Login {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void loginMenu(Scanner scanner) {
        int type;

        do {
            System.out.println(ANSI_CYAN + "╔═════════════════════════════════╗");
            System.out.println("║          Login Menu             ║");
            System.out.println("╠═════════════════════════════════╣");
            System.out.println("║ 1. " + ANSI_YELLOW + "Student Login" + ANSI_CYAN + "                ║");
            System.out.println("║ 2. " + ANSI_YELLOW + "Teacher Login" + ANSI_CYAN + "                ║");
            System.out.println("║ 0. " + ANSI_RED + "Back" + ANSI_CYAN + "                         ║");
            System.out.println("╚═════════════════════════════════╝" + ANSI_RESET);

            System.out.print("Select an option: ");
            type = scanner.nextInt();

            switch (type) {
                case 1:
                    System.out.println(ANSI_CYAN + "╔═════════════════════════════════╗");
                    System.out.println("║         Student Login           ║");
                    System.out.println("╚═════════════════════════════════╝" + ANSI_RESET);
                    view.Login.StudentLogin.studentLogin(scanner);
                    break;
                case 2:
                    System.out.println(ANSI_CYAN + "╔═════════════════════════════════╗");
                    System.out.println("║         Teacher Login           ║");
                    System.out.println("╚═════════════════════════════════╝" + ANSI_RESET);
                    view.Login.TeacherLogin.teacherLogin(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option. Please select a valid option." + ANSI_RESET);
                    break;
            }
        } while (type != 0);
    }
}
