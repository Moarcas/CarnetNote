package view.StudentMenu;

import java.util.List;
import java.util.Scanner;

import controller.MaterieController;
import controller.StudentController;
import model.dao.MaterieDAO;
import model.entity.Materie;
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
            System.out.println("║ 1. " + ANSI_YELLOW + "Enroll in Course" + ANSI_CYAN + "                    ║");
            System.out.println("║ 2. " + ANSI_YELLOW + "View Grades" + ANSI_CYAN + "                         ║");
            System.out.println("║ 3. " + ANSI_YELLOW + "View Courses" + ANSI_CYAN + "                        ║");
            System.out.println("║ 4. " + ANSI_YELLOW + "Show Profile" + ANSI_CYAN + "                        ║");
            System.out.println("║ 0. " + ANSI_YELLOW + "Exit" + ANSI_CYAN + "                                ║");
            System.out.println("╚════════════════════════════════════════╝" + ANSI_RESET);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(ANSI_CYAN + "Enroll in Coruse" + ANSI_RESET);

                    // Show all courses
                    MaterieController materieController = MaterieController.getInstance();
                    List<Materie> materii = materieController.getAllCourses();

                    view.Courses.ShowCourses.showCourses(materii);

                    // Ask user to choose a course
                    System.out.print("Enter the number of the course you want to enroll in: ");

                    int courseNumber = scanner.nextInt();

                    if (courseNumber < 1 || courseNumber > materii.size()) {
                        System.out.println(ANSI_CYAN + "Invalid choice. Please try again." + ANSI_RESET);
                        break;
                    }

                    // Enroll user in course
                    Materie materie = materii.get(courseNumber - 1);
                    StudentController studentController = StudentController.getInstance();
                    studentController.enrollInCourse(user.getId(), materie.getId());

                    System.out.println(ANSI_CYAN + "You have been enrolled in " + materie.getNume() + ANSI_RESET);
                    break;
                case 2:
                    System.out.println(ANSI_CYAN + "View Classes" + ANSI_RESET);
                    // Implementează logica pentru vizualizarea notelor
                    break;
                case 3:
                    System.out.println(ANSI_CYAN + "View courses" + ANSI_RESET);
                    view.StudentMenu.ShowClasses.showClasses(user);
                    break;
                case 4:
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
