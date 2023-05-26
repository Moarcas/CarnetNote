package view.TeacherMenu;

import java.util.List;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

import controller.GrupaController;
import controller.MaterieController;
import controller.ProfesorController;
import exceptions.DatabaseException;
import exceptions.ProfesorAlreadyEnrolled;
import model.entity.Grupa;
import model.entity.Materie;
import model.entity.Profesor;

public class TeacherMenu {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void showMenu(Scanner scanner, Profesor user) {
        boolean exit = false;

        while (!exit) {
            System.out.println(ANSI_CYAN + "╔════════════════════════════════════════╗");
            System.out.println("║" + ANSI_YELLOW + "              Teacher Menu              " + ANSI_CYAN + "║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. " + ANSI_YELLOW + "Add Grade" + ANSI_CYAN + "                           ║");
            System.out.println("║ 2. " + ANSI_YELLOW + "Enroll in Course and Class" + ANSI_CYAN + "                     ║");
            System.out.println("║ 3. " + ANSI_YELLOW + "View Profile" + ANSI_CYAN + "                        ║");
            System.out.println("║ 0. " + ANSI_RED + "Logout" + ANSI_CYAN + "                              ║");
            System.out.println("╚════════════════════════════════════════╝" + ANSI_RESET);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(ANSI_CYAN + "Add grade" + ANSI_RESET);
                    int selectedCourseId = view.TeacherMenu.ShowTeacherCourses.showCourses(scanner, user);

                    if (selectedCourseId == -1) {
                        break;
                    }

                    int selectedClassId = view.TeacherMenu.ShowTeacherClasses.showClasses(scanner, user.getId(), selectedCourseId);

                    if (selectedClassId == -1) {
                        break;
                    }

                    view.TeacherMenu.ShowTeacherStudents.showStudents(scanner, selectedCourseId, selectedClassId);

                    break;
                case 2:
                    System.out.println(ANSI_CYAN + "Enroll in Course and Class" + ANSI_RESET);

                    // Show all courses
                    MaterieController materieController = MaterieController.getInstance();
                    List<Materie> materii = materieController.getAllCourses();
                    view.Courses.ShowCourses.showCourses(materii);
                    
                    if (materii.size() == 0) {
                        break;
                    }

                    // Ask user to select a course
                    System.out.print("Enter the number of the course you want to enroll in: ");

                    int courseNumber = scanner.nextInt();

                    if (courseNumber < 1 || courseNumber > materii.size()) {
                        System.out.println(ANSI_RED + "\nInvalid course number. Please try again.\n" + ANSI_RESET);
                        break;
                    }
                    
                    // Show all classes
                    GrupaController grupaController = GrupaController.getInstance();
                    List<Grupa> grupe = grupaController.getAllClasses();
                    view.Classes.ShowClasses.showClasses(grupe);

                    if (grupe.size() == 0) {
                        break;
                    }

                    // Ask user to select a class
                    System.out.println("Select the class where you want to teach " + materii.get(courseNumber - 1).getNume() + ":");
                    
                    int classNumber = scanner.nextInt();

                    if (classNumber < 1 || classNumber > grupe.size()) {
                        System.out.println(ANSI_RED + "\nInvalid class number. Please try again.\n" + ANSI_RESET);
                        break;
                    }

                    // Enroll teacher in course and class
                    Materie materie = materii.get(courseNumber - 1);
                    Grupa grupa = grupe.get(classNumber - 1);
                    ProfesorController profesorController = ProfesorController.getInstance();

                    try {
                        profesorController.enrollInCourseAndClass(user.getId(), materie.getId(), grupa.getId());
                        System.out.println(ANSI_YELLOW + "\nYou have successfully enrolled in " + materii.get(courseNumber - 1).getNume() + " and " + grupe.get(classNumber - 1).getNume() + ".\n" + ANSI_RESET);
                    } catch(ProfesorAlreadyEnrolled | DatabaseException e) {
                        System.out.println(ANSI_RED + "\n" + e.getMessage() + "\n" + ANSI_RESET);
                    }

                    break;
                    case 6:
                    System.out.println(ANSI_CYAN + "View Profile" + ANSI_RESET);
                    // Implement code for viewing profile
                    break;
                    
                case 3:
                    System.out.println(ANSI_CYAN + "View Profile" + ANSI_RESET);
                    // Implement code for viewing profile
                    break;
                case 0:
                    exit = true;
                    System.out.println(ANSI_CYAN + "\nLogging out...\n" + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_RED + "\nInvalid choice. Please try again.\n" + ANSI_RESET);
                    break;
            }
        }
    }
}
