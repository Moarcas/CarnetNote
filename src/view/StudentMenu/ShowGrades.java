package view.StudentMenu;

import java.util.List;

import controller.NotaController;
import model.entity.Materie;
import model.entity.Nota;

public class ShowGrades {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void showGrades(int userId, Materie materie) {
        NotaController notaController = NotaController.getInstance();
        List<Nota> note = notaController.getNoteByStudent(userId, materie.getId());

        if (note.isEmpty()) {
            System.out.println(ANSI_RED + "\nNo grades available\n" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Grades:" + ANSI_RESET);
        for (int i = 0; i < note.size(); i++) {
            Nota nota = note.get(i);
            int gradeNumber = i + 1;
            System.out.println(ANSI_YELLOW + "Grade " + gradeNumber + ":" + ANSI_RESET);
            System.out.println("Course: " + materie.getNume());
            System.out.println("Grade: " + nota.getNota());
            System.out.println("Date: " + nota.getData());
            System.out.println();
        }
    }
}
    