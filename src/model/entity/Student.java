package model.entity;

public class Student extends User{
    private int an;
    private int grupa;

    public Student() { 
    }

    public Student(int id, String nume, String prenume, String email, String passwordHash, int an, int grupa) {
        super(id, nume, prenume, email, passwordHash, "student");
        this.an = an;
        this.grupa = grupa;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

}
