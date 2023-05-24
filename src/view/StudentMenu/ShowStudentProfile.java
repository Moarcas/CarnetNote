package view.StudentMenu;

import exceptions.GrupaNotFoundException;
import model.entity.Student;
import services.GrupaService;

public class ShowStudentProfile {
    public static void showProfile(Student user) {
        System.out.println("Student Profile:");
        System.out.println("Nume: " + user.getNume());
        System.out.println("Prenume: " + user.getPrenume());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Rol: " + user.getRol());

        GrupaService grupaService = GrupaService.getInstance();
        try {
            System.out.println("Grupa: " + grupaService.getGrupaById(user.getIdGrupa()).getNume());
        } catch (GrupaNotFoundException e) {
            System.out.println("Grupa nu exista");
            e.printStackTrace();
        }
    }
}
