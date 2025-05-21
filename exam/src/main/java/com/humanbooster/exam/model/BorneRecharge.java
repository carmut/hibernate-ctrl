package com.humanbooster.exam.model;

import com.humanbooster.exam.model.enums.EtatBorne;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "borne_recharge")
public class BorneRecharge {

    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name = "etat")
    private EtatBorne etat = EtatBorne.DISPONIBLE;

    @NotNull
    @Column(nullable = false, name = "tarifHoraire")
    private Double tarifHoraire;


    //relations
    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lieu_id")
    private LieuRecharge lieu;


    //contructor
    public BorneRecharge(Double tarifHoraire,LieuRecharge lieu) {
        this.tarifHoraire = tarifHoraire;
        this.lieu = lieu;
    }
    public BorneRecharge() {}


    //getter and setter
    public Long getId() {
        return id;
    }

    public Double getTarifHoraire() {
        return tarifHoraire;
    }
    public void setTarifHoraire(Double tarifHoraire) {
        this.tarifHoraire = tarifHoraire;
    }

    public EtatBorne getEtat() {
        return etat;
    }
    public void setEtat(EtatBorne etat) {
        this.etat = etat;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public LieuRecharge getLieu() {
        return lieu;
    }
    public void setLieu(LieuRecharge lieu) {
        this.lieu = lieu;
    }

}
