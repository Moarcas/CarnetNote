package view.TeacherMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.entity.Materie;
import model.entity.Profesor;

public class ShowTeacherCourses {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static int showCourses(Scanner scanner, Profesor user) {
        Set<Materie> materii = user.getMateriiPredate();

        if (materii.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return -1;
        }
        
        List<Materie> materiiList = new ArrayList<>(materii);
        
        boolean exit = false;

        while (!exit) {
            System.out.println("Courses for teacher: " + user.getNume() + " " + user.getPrenume());
            for (int i = 0; i < materii.size(); i++) {
                System.out.println(i + 1 + ". " + materiiList.get(i).getNume());
            }

            System.out.println("0. Go back");
    
            System.out.print("Select a course: ");
            int choice = scanner.nextInt();

            if (choice < 0 || choice > materii.size()) {
                System.out.println(ANSI_RED + "Invalid option. Please select a valid option." + ANSI_RESET);
                continue;
            }

            if (choice == 0) {
                exit = true;
                continue;
            }

            return materiiList.get(choice - 1).getId();
        }
        return -1;
    }
}       
