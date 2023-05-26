package view.Classes;

import java.util.List;

import model.entity.Grupa;

public class ShowClasses {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void showClasses(List<Grupa> grupe) {
        if (grupe.isEmpty()) {
            System.out.println(ANSI_RED + "No classes available" + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Classes:" + ANSI_RESET);
        for (int i = 0; i < grupe.size(); i++) {
            Grupa grupa = grupe.get(i);
            int classNumber = i + 1;
            System.out.println(ANSI_YELLOW + "Class " + classNumber + ":" + ANSI_RESET);
            System.out.println("Name: " + grupa.getNume());
            System.out.println("An: " + grupa.getAn());
            System.out.println();
        }
    }
}

