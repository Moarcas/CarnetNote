package model;

import java.util.Objects;

public abstract class User {
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String passwordHash; 
    private String rol;

    public User() {
    }

    public User(int id, String nume, String prenume, String email, String passwordHash, String rol) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
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

    public String getPrenume() {
        return this.prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public User id(int id) {
        setId(id);
        return this;
    }

    public User nume(String nume) {
        setNume(nume);
        return this;
    }

    public User prenume(String prenume) {
        setPrenume(prenume);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User passwordHash(String passwordHash) {
        setPasswordHash(passwordHash);
        return this;
    }

    public User rol(String rol) {
        setRol(rol);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(nume, user.nume) && Objects.equals(prenume, user.prenume) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(rol, user.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, email, passwordHash, rol);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nume='" + getNume() + "'" +
            ", prenume='" + getPrenume() + "'" +
            ", email='" + getEmail() + "'" +
            ", passwordHash='" + getPasswordHash() + "'" +
            ", rol='" + getRol() + "'" +
            "}";
    }
}
