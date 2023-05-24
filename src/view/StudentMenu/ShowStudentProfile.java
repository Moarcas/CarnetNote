package view.StudentMenu;

import exceptions.GrupaNotFoundException;
import model.entity.Student;
import services.GrupaService;

public class ShowStudentProfile {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void showProfile(Student user) {
        System.out.println(ANSI_CYAN + "\nStudent Profile:" + ANSI_RESET);
        System.out.println("Nume: " + user.getNume());
        System.out.println("Prenume: " + user.getPrenume());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Rol: " + user.getRol());

        GrupaService grupaService = GrupaService.getInstance();
        try {
            System.out.println("Grupa: " + grupaService.getGrupaById(user.getIdGrupa()).getNume());
        } catch (GrupaNotFoundException e) {
            System.out.println(ANSI_RED + "Grupa nu exista" + ANSI_RESET);
        }
    }
}
