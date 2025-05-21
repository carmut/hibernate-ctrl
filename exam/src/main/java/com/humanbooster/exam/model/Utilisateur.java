package com.humanbooster.exam.model;

import com.humanbooster.exam.model.enums.RoleUtilisateur;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @NotNull
    @Column(nullable = false, name = "email")
    private String email;

    @NotNull
    @Column(nullable = false, name = "motDePasse")
    private String motDePasse;

    @Column(name = "codeValidation")
    private int codeValidation;

    @ColumnDefault("false")
    @Column(name = "valide")
    private boolean valide = false;

    @ColumnDefault("UTILISATEUR")
    @Column(name = "role")
    private RoleUtilisateur role = RoleUtilisateur.UTILISATEUR;


    //relation
    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    //contructor
    public Utilisateur(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }
    public Utilisateur() {}


    //getter and setter
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCodeValidation() {
        return codeValidation;
    }
    public void setCodeValidation(int codeValidation) {
        this.codeValidation = codeValidation;
    }

    public boolean isValide() {
        return valide;
    }
    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public RoleUtilisateur getRole() {
        return role;
    }
    public void setRole(RoleUtilisateur role) {
        this.role = role;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
