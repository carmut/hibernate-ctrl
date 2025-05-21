package com.humanbooster.exam.model;

import com.humanbooster.exam.dao.interfaces.AuthentificationService;
import com.humanbooster.exam.model.enums.RoleUtilisateur;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements AuthentificationService {

    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    public Long getId() {
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

        //ajouter pour les tests
        for(Reservation r : reservations ) {
            System.out.println(r.getDateDebut() + " <--> " + r.getDateFin());
        }
    }

    //fonction
    public void generateCodeValidation() {
        this.codeValidation = (int) (Math.random() * 9000 + 1000);
    }

    public void validation(int code){
        if(code == this.codeValidation){
            this.valide = true;
            System.out.println("validation effectuer avec succee");
        } else {
            System.out.println("code invalide");
            System.out.println("votre code est : " + codeValidation);
        }
    }

    public void enregistrer(){
        Scanner inputregister = new Scanner(System.in);

        System.out.println("vueillez renseigner votre email : ");
        String mail = inputregister.next();
        inputregister.nextLine();
        this.email = mail;

        System.out.println("vueillez renseigner votre mot de passe : ");
        String password = inputregister.next();
        inputregister.nextLine();
        this.motDePasse = password;

        generateCodeValidation();

        System.out.println("====== email simulé ======");
        System.out.println("votre de code de validation est le : ");
        System.out.println(getCodeValidation());
    }

    public void connection(String email, String motDePasse){
        if(this.valide){
            if (this.email.equals(email) && this.motDePasse.equals(motDePasse)){
                System.out.println("connection réussi");
            }else {
                System.out.println("identifiant invalide");
            }
        }else {
            System.out.println("compte non valider");
        }
    }


    //fonctions de test
    public int enregistrer_test(String email, String motDePasse){
        this.email = email;
        this.motDePasse = motDePasse;

        generateCodeValidation();

        System.out.println("====== email simulé ======");
        System.out.println("votre de code de validation est le : ");
        System.out.println(getCodeValidation());

        return codeValidation;
    }
}
