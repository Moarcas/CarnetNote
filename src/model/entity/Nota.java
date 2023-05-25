package model.entity;

public class Nota {
    private int id;
    private int idStudent;
    private int idMaterie;
    private float nota;
    private String data;

    public Nota(int idStudent, int idMaterie, float nota, String data) {
        this.idStudent = idStudent;
        this.idMaterie = idMaterie;
        this.nota = nota;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdMaterie() {
        return idMaterie;
    }

    public float getNota() {
        return nota;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public void setIdMaterie(int idMaterie) {
        this.idMaterie = idMaterie;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", idMaterie=" + idMaterie +
                ", nota=" + nota +
                ", data='" + data + '\'' +
                '}';
    }
}
