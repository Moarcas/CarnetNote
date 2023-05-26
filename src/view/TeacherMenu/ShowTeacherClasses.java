package view.TeacherMenu;

import java.util.List;
import java.util.Scanner;

import controller.GrupaController;
import model.entity.Grupa;

public class ShowTeacherClasses {
    public static int showClasses(Scanner scanner, int idProfesor, int idMaterie) {
        
        // Get classes where the teacher is teaching at materie
        GrupaController grupaController = GrupaController.getInstance();

        List<Grupa> grupe = grupaController.getGrupeByTeacherAndCourse(idProfesor, idMaterie);

        if (grupe.isEmpty()) {
            System.out.println("\nYou are not teaching at any classes.\n");
            return -1;
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("Your classes:");
            for (int i = 0; i < grupe.size(); i++) {
                System.out.println(i + 1 + ". " + grupe.get(i).getNume());
            }

            System.out.println("0. Go back");
    
            System.out.print("Select a class: ");
            int choice = scanner.nextInt();

            if (choice < 0 || choice > grupe.size()) {
                System.out.println(ShowTeacherCourses.ANSI_RED + "Invalid option. Please select a valid option." + ShowTeacherCourses.ANSI_RESET);
                continue;
            }

            if (choice == 0) {
                exit = true;
                continue;
            }

            return grupe.get(choice - 1).getId();
        }

        return -1;
    }
}
