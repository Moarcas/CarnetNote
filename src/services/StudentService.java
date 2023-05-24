package services;

public class StudentService extends UserService {
    private static StudentService instance = null;
    
    public StudentService() {}

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }


    
}
