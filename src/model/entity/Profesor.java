package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends User {
    // Materiile la care preda
    private List<Materie> materiiPredate;

    // Grupele la care preada
    private List<Grupa> grupe;

    public Profesor() {
        materiiPredate = new ArrayList<>();
        grupe = new ArrayList<>();
    }

    public Profesor(List<Materie> materiiPredate, List<Grupa> grupe) {
        this.materiiPredate = materiiPredate;
        this.grupe = grupe;
    }

    public Profesor(String nume, String prenume, String email, String passwordHash, List<Materie> materiiPredate, List<Grupa> grupe) {
        super(nume, prenume, email, passwordHash, "profesor");
        this.materiiPredate = materiiPredate;
        this.grupe = grupe;
    }

    public List<Materie> getMateriiPredate() {
        return this.materiiPredate;
    }

    public void setMateriiPredate(List<Materie> materiiPredate) {
        this.materiiPredate = materiiPredate;
    }

    public List<Grupa> getGrupe() {
        return this.grupe;
    }

    public void setGrupe(List<Grupa> grupe) {
        this.grupe = grupe;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
            " materiiPredate='" + getMateriiPredate() + "'" +
            ", grupe='" + getGrupe() + "'" +
            "}";
    }

}
