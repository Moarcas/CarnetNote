    // package controller;
 
    // import exceptions.EmailAlreadyUser;
    // import model.entity.Student;
    // import model.entity.User;
    // import services.StudentService;
    // import services.UserService;

    // public class UserController {
    //     private UserService userService = new StudentService();
    //     private static UserController instance = null;
        
    //     public UserController() {}

    //     public static synchronized UserController getInstance() {
    //         if (instance == null) {
    //             instance = new UserController();
    //         }
    //         return instance;
    //     } 

    //     public void createAccount(String nume, String prenume, String email, String password, String rol) {
    //         User user;

    //         if (rol == "student") {
    //             user = new Student(nume, prenume, email, password, );
    //             try {
    //                 userService.registerUser(user);
    //             } catch (EmailAlreadyUser e) {
    //                 System.out.println("Adresa de mail a mai fost deja folosita");
    //                 e.printStackTrace();
    //             }
    //         }
    //     }

    // }