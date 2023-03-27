package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Grupa {
    private int id;
    private String nume;
    private int an;
    private List<Student> studenti;

    public Grupa() {
        studenti = new ArrayList<>();
    }

    public Grupa(String nume, int an, List<Student> studenti) {
        this.nume = nume;
        this.an = an;
        this.studenti = studenti;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return this.nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getAn() {
        return this.an;     
    }

    public void setAn(int an) {
        this.an = an;
    }

    public List<Student> getStudenti() {
        return this.studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nume='" + getNume() + "'" +
            ", an='" + getAn() + "'" +
            ", studenti='" + getStudenti() + "'" +
            "}";
    }

}
