package view.Courses;

import java.util.List;

import model.entity.Materie;

public class ShowCourses {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void showCourses(List<Materie> materii) {
        if (materii.isEmpty()) {
            System.out.println(ANSI_RED + "No courses available" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Courses:" + ANSI_RESET);
        for (int i = 0; i < materii.size(); i++) {
            Materie materie = materii.get(i);
            int courseNumber = i + 1;
            System.out.println(ANSI_YELLOW + "Course " + courseNumber + ":" + ANSI_RESET);
            System.out.println("Name: " + materie.getNume());
            System.out.println("Description: " + materie.getDescriere());
            System.out.println();
        }
    }
}
