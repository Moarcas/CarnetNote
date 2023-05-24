package model.entity;

public class Grupa {
    private int id;
    private String nume;
    private int an;

    public Grupa(String nume, int an) {
        this.nume = nume;
        this.an = an;
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nume='" + getNume() + "'" +
            ", an='" + getAn() + "'" +
            "}";
    }

}
