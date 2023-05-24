package view.StudentMenu;

import java.util.Scanner;

import model.entity.Student;

public class StudentMenu {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void showMenu(Scanner scanner, Student user) {
        boolean exit = false;

        while (!exit) {
            System.out.println(ANSI_CYAN + "╔════════════════════════════════════════╗");
            System.out.println("║" + ANSI_YELLOW + "              Student Menu              " + ANSI_CYAN + "║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. " + ANSI_YELLOW + "View Grades" + ANSI_CYAN + "                         ║");
            System.out.println("║ 2. " + ANSI_YELLOW + "View Courses" + ANSI_CYAN + "                        ║");
            System.out.println("║ 3. " + ANSI_YELLOW + "Show Profile" + ANSI_CYAN + "                        ║");
            System.out.println("║ 0. " + ANSI_YELLOW + "Exit" + ANSI_CYAN + "                                ║");
            System.out.println("╚════════════════════════════════════════╝" + ANSI_RESET);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(ANSI_CYAN + "View Classes" + ANSI_RESET);
                    // Implementează logica pentru vizualizarea notelor
                    break;
                case 2:
                    System.out.println(ANSI_CYAN + "Enroll in Courses" + ANSI_RESET);
                    // Implementează logica pentru înrolarea în cursuri
                    break;
                case 3:
                    System.out.println(ANSI_CYAN + "Show profile" + ANSI_RESET);
                    view.StudentMenu.ShowStudentProfile.showProfile(user);
                    break;
                case 0:
                    exit = true;
                    System.out.println(ANSI_CYAN + "Exiting Student Menu. Goodbye!" + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_CYAN + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }
}
