package model.entity;

public class Student extends User{
    private int idGrupa;

    public Student() { 
    }

    public Student(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public Student(String nume, String prenume, String email, String passwordHash, int idGrupa) {
        super(nume, prenume, email, passwordHash, "student");
        this.idGrupa = idGrupa;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
            " idGrupa='" + getIdGrupa() + "'" +
            "}";
    }


}
