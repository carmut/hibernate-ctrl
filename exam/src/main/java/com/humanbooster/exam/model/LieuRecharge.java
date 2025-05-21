package com.humanbooster.exam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lieu_rechage")
public class LieuRecharge {

    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name = "nom")
    private String nom;

    @NotNull
    @Column(nullable = false, name = "adresse")
    private String adresse;


    //relation
    @OneToMany(mappedBy = "lieu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorneRecharge> bornes = new ArrayList<>();

    //contructor
    public LieuRecharge(String nom, String adresse) {
        this.adresse = adresse;
        this.nom = nom;
    }
    public LieuRecharge() {}


    //getter and setter
    public Long getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<BorneRecharge> getBornes() {
        return bornes;
    }
    public void setBornes(List<BorneRecharge> bornes) {
        this.bornes = bornes;
    }
    public void addBorne(BorneRecharge borne) {
        bornes.add(borne);
    }
}
