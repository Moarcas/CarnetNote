package view.TeacherMenu;

import java.util.List;
import java.util.Scanner;

import controller.MaterieController;
import controller.NotaController;
import controller.StudentController;
import model.entity.Student;

public class ShowTeacherStudents {
    public static void showStudents(Scanner scanner, int idMaterie, int idGrupa) {
        
        StudentController studentController = StudentController.getInstance();

        List<Student> students = studentController.getStudentsByMaterieAndGrupa(idMaterie, idGrupa);

        if (students.isEmpty()) {
            System.out.println("\nThere are no students enrolled in this class.\n");
            return;
        }
        System.out.println(students.get(0));

        boolean exit = false;

        while (!exit) {
            System.out.println("Students:");
            for (int i = 0; i < students.size(); i++) {
                System.out.println(i + 1 + ". " + students.get(i).getNume() + " " + students.get(i).getPrenume());

                // Show grades
                view.StudentMenu.ShowGrades.showGrades(students.get(i).getId(), MaterieController.getInstance().getCourseById(idMaterie));
            }

            System.out.println("0. Go back");
    
            System.out.print("Select a student: ");
            int choice = scanner.nextInt();

            if (choice < 0 || choice > students.size()) {
                System.out.println(ShowTeacherCourses.ANSI_RED + "Invalid option. Please select a valid option." + ShowTeacherCourses.ANSI_RESET);
                continue;
            }

            if (choice == 0) {
                exit = true;
                continue;
            }

            Student student = students.get(choice - 1);

            // Ask teacher what grade to give
            System.out.print("Enter grade: ");
            float grade = scanner.nextFloat();

            // Add grade to student
            String date = "2023-05-26";
            NotaController.getInstance().addNota(student.getId(), idMaterie, grade, date);
        }
    }
}
