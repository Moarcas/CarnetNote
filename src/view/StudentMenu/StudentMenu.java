package view.StudentMenu;

import java.util.List;
import java.util.Scanner;

import controller.MaterieController;
import controller.StudentController;
import exceptions.DatabaseException;
import exceptions.StudentAlreadyEnrolledException;
import model.entity.Materie;
import model.entity.Student;

public class StudentMenu {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";


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
            System.out.println("║ 0. " + ANSI_RED + "Logout" + ANSI_CYAN + "                              ║");
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
                        System.out.println(ANSI_CYAN + "\nInvalid choice. Please try again.\n" + ANSI_RESET);
                        break;
                    }

                    // Enroll user in course
                    Materie materie = materii.get(courseNumber - 1);
                    StudentController studentController = StudentController.getInstance();

                    try {
                        studentController.enrollInCourse(user.getId(), materie.getId());
                        System.out.println(ANSI_CYAN + "\nYou have been enrolled in " + materie.getNume() + "\n" + ANSI_RESET);
                    } catch(StudentAlreadyEnrolledException | DatabaseException e) {
                        System.out.println(ANSI_RED + "\n" + e.getMessage() + "\n" + ANSI_RESET);
                    }

                break;
                case 2:
                    System.out.println(ANSI_CYAN + "View Grades" + ANSI_RESET);

                    // Show all courses the user is enrolled in
                    materieController = MaterieController.getInstance();
                    List<Materie> materiiUser = materieController.getCoursesByStudent(user.getId());
                    view.Courses.ShowCourses.showCourses(materiiUser);

                    // Ask user to choose a course
                    System.out.print("Enter the number of the course you want to view grades for: ");

                    courseNumber = scanner.nextInt();
                    
                    if (courseNumber < 1 || courseNumber > materiiUser.size()) {
                        System.out.println(ANSI_CYAN + "\nInvalid choice. Please try again.\n" + ANSI_RESET);
                        break;
                    }

                    // Show grades for course
                    materie = materiiUser.get(courseNumber - 1);
                    view.StudentMenu.ShowGrades.showGrades(user.getId(), materie);

                    break;
                case 3:
                    System.out.println(ANSI_CYAN + "View courses" + ANSI_RESET);
                    view.StudentMenu.ShowStudentCourses.showCourses(user);
                    break;
                case 4:
                    System.out.println(ANSI_CYAN + "Show profile" + ANSI_RESET);
                    view.StudentMenu.ShowStudentProfile.showProfile(user);
                    break;
                case 0:
                    exit = true;
                    System.out.println(ANSI_CYAN + "\nLogging out...\n" + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_RED + "\nInvalid choice. Please try again.\n" + ANSI_RESET);
            }
        }
    }
}
