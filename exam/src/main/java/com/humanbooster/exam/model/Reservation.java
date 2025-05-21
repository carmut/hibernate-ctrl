package com.humanbooster.exam.model;

import com.humanbooster.exam.model.enums.StatutReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Reservation {

    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name = "dateDebut")
    private Date dateDebut;

    @NotNull
    @Column(nullable = false, name = "dateFin")
    private Date dateFin;

    @NotNull

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
    public Reservation(Date dateDebut, Date dateFin, Utilisateur utilisateur, BorneRecharge borne) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateur = utilisateur;
        this.borne = borne;
    }
    public Reservation() {}


    //getter and setter
    public Long getId() {
        return id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
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
