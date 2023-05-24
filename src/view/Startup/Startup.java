package view.Startup;

import java.util.Scanner;

public class Startup {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println(ANSI_CYAN + "╔═══════════════════════════════════════╗");
            System.out.println("║" + ANSI_PURPLE + "               MENIU                   " + ANSI_CYAN + "║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║ 1. " + ANSI_YELLOW + "Login" + ANSI_CYAN + "                              ║");
            System.out.println("║ 2. " + ANSI_YELLOW + "Register" + ANSI_CYAN + "                           ║");
            System.out.println("║ 0. " + ANSI_RED + "Ieșire" + ANSI_CYAN + "                             ║");
            System.out.println("╚═══════════════════════════════════════╝" + ANSI_RESET);

            System.out.print("Selectați o opțiune: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(ANSI_CYAN + "╔═══════════════════════════════════════╗");
                    System.out.println("║" + ANSI_PURPLE + "               LOGIN                   " + ANSI_CYAN + "║");
                    System.out.println("╚═══════════════════════════════════════╝" + ANSI_RESET);
                    view.Login.Login.loginMenu(scanner);
                    break;
                case 2:
                    System.out.println(ANSI_CYAN + "╔═══════════════════════════════════════╗");
                    System.out.println("║" + ANSI_PURPLE + "             REGISTER                  " + ANSI_CYAN + "║");
                    System.out.println("╚═══════════════════════════════════════╝" + ANSI_RESET);
                    System.out.println("Opțiunea de register a fost selectată.");
                    view.Register.Register.registerMenu(scanner);
                    break;
                case 0:
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println(ANSI_RED + "Opțiune invalidă. Vă rugăm să selectați o opțiune validă." + ANSI_RESET);
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}
