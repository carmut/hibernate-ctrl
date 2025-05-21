package com.humanbooster.exam.model;

import com.humanbooster.exam.model.enums.StatutReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
public class Reservation {

    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false, name = "dateDebut")
    private LocalDate dateDebut;

    @NotNull
    @Column(nullable = false, name = "dateFin")
    private LocalDate dateFin;

    @NotNull
    @ColumnDefault("EN_ATTENTE")
    @Column(nullable = false, name = "statut")
    private StatutReservation statut = StatutReservation.EN_ATTENTE;


    //relations
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borne_id")
    private BorneRecharge borne;

    //contructor
    public Reservation(LocalDate dateDebut, LocalDate dateFin, Utilisateur utilisateur, BorneRecharge borne) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateur = utilisateur;
        this.borne = borne;
    }
    public Reservation() {}


    //getter and setter
    public int getId() {
        return id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutReservation getStatut() {
        return statut;
    }
    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public BorneRecharge getBorne() {
        return borne;
    }
    public void setBorne(BorneRecharge borne) {
        this.borne = borne;
    }
}
