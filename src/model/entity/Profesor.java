package model.entity;

import java.util.HashSet;
import java.util.Set;

public class Profesor extends User {
    // Materiile la care preda
    private Set<Materie> materiiPredate;

    // Grupele la care preada
    private Set<Grupa> grupe;

    public Profesor() { 
        materiiPredate = new HashSet<>();
        grupe = new HashSet<>();
    }

    public Profesor(Set<Materie> materiiPredate, Set<Grupa> grupe) {
        this.materiiPredate = materiiPredate;
        this.grupe = grupe;
    }

    public Profesor(String nume, String prenume, String email, String passwordHash) {
        super(nume, prenume, email, passwordHash, "profesor");
        materiiPredate = new HashSet<>();
        grupe = new HashSet<>();
    }

    public Set<Materie> getMateriiPredate() {
        return this.materiiPredate;
    }

    public void setMateriiPredate(Set<Materie> materiiPredate) {
        this.materiiPredate = Set.copyOf(materiiPredate);
    }

    public Set<Grupa> getGrupe() {
        return this.grupe;
    }

    public void setGrupe(Set<Grupa> grupe) {
        this.grupe = Set.copyOf(grupe);
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
            " materiiPredate='" + getMateriiPredate() + "'" +
            ", grupe='" + getGrupe() + "'" +
            "}";
    }

}
