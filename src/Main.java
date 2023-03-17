import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import model.dao.StudentDAO;
import model.dao.UserDAO;
import model.entity.Student;
import model.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = UserDAO.getInstance();
        Student student = new Student(0, "Moarcas", "Cosmin", "test@yahoo.com", "123456", 2021, 251);
        Student student2 = new Student(2, "Moarcas", "Cosmin", "test@yahoo.com", "123456", 2021, 251);
        userDAO.addUser(student);   
        userDAO.addUser(student2);   
    }
}