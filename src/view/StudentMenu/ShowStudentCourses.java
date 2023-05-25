package view.StudentMenu;

import java.util.List;

import controller.StudentController;
import model.entity.Materie;
import model.entity.Student;

public class ShowStudentCourses {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void showClasses(Student user) {
        StudentController studentController = StudentController.getInstance();
        List<Materie> materii = studentController.getMaterii(user.getId());

        if (materii.isEmpty()) {
            System.out.println(ANSI_RED + "No classes" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "----------------------------------------");
        System.out.println("Classes for student: " + user.getNume() + " " + user.getPrenume());
        System.out.println("----------------------------------------" + ANSI_RESET);

        for (Materie materie : materii) {
            System.out.println(ANSI_GREEN + "- " + materie.getNume() + ANSI_RESET);
        }
    }
}
