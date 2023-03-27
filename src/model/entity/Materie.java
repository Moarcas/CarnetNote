package model.entity;

import java.util.Objects;

public class Materie {
    private int id;
    private String nume;
    private String descriere;
 
    public Materie() {
    }

    public Materie(String nume, String descriere) {
        this.nume = nume;
        this.descriere = descriere;
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

    public String getDescriere() {
        return this.descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Materie id(int id) {
        setId(id);
        return this;
    }

    public Materie nume(String nume) {
        setNume(nume);
        return this;
    }

    public Materie descriere(String descriere) {
        setDescriere(descriere);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Materie)) {
            return false;
        }
        Materie materie = (Materie) o;
        return id == materie.id && Objects.equals(nume, materie.nume) && Objects.equals(descriere, materie.descriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, descriere);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nume='" + getNume() + "'" +
            ", descriere='" + getDescriere() + "'" +
            "}";
    }
}
