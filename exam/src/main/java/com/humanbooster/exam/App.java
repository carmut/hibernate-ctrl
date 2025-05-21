package com.humanbooster.exam;

import com.humanbooster.exam.dao.BorneRechargeDao;
import com.humanbooster.exam.dao.LieuRechargeDao;
import com.humanbooster.exam.dao.ReservationDao;
import com.humanbooster.exam.dao.UtilisateurDao;
import com.humanbooster.exam.model.BorneRecharge;
import com.humanbooster.exam.model.LieuRecharge;
import com.humanbooster.exam.model.Reservation;
import com.humanbooster.exam.model.Utilisateur;
import com.humanbooster.exam.model.enums.EtatBorne;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    //global
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    //initialisation des objet

        //infos user
        String user_email = "test@test.com";
        String user_motDePasse = "test";
        int user_code;

        //infos LieuRecharge
        String lieu_nom = "lieu de test";
        String lieu_adresse = "10 rue du test , 63000 test-ville";

        //infos borne
        Double borne_tarifHoraire = 15.50;

        //infos reservation

        cal.set(2025, Calendar.APRIL, 25, 9, 0, 0);
        Date reservation_date_debut = cal.getTime();

        cal.set(2025, Calendar.APRIL, 26, 9, 0, 0);
        Date reservation_date_fin = cal.getTime();

        //création des class
        Utilisateur user = new Utilisateur();
        LieuRecharge lieu = new LieuRecharge(lieu_nom, lieu_adresse);
        BorneRecharge borne = new BorneRecharge(borne_tarifHoraire, lieu);
        Reservation reservation = new Reservation(reservation_date_debut,reservation_date_fin,user,borne);


    //test pour l'utilisateur
        //resultat attendue : faux email avec code validation
        //!!fonction adapter pour faciliter les tests ; version de base disponible sans les arguments!!
        user_code = user.enregistrer_test(user_email, user_motDePasse);

        //resultat attendue : validation effectuer avec succee
        user.validation(user_code);

        //resultat attendue : connection réussi
        user.connection(user_email, user_motDePasse);

        //resultat attendue : 25-avril-2025 9h <--> 26-avril-2025 9h
        user.addReservation(reservation);

    //test pour LieuRecharge
        lieu.addBorne(borne);
    //test pour BorneRecharge
        borne.addReservation(reservation);
    //test pour Reservation
        



//        ******************************
//                     DAO
//        ******************************
        //creation DAO
        UtilisateurDao userDao = new UtilisateurDao(sessionFactory);
        LieuRechargeDao lieuDao = new LieuRechargeDao(sessionFactory);
        BorneRechargeDao borneDao = new BorneRechargeDao(sessionFactory);
        ReservationDao reservationDao = new ReservationDao(sessionFactory);


    //test pour utilisateurDao
        //resultat attendue :entitée Utilisateur creer avec succes
            userDao.creer(user);

        //sauvegarder de l'id de l'user de test
            Long user_id = user.getId();

        //resultat attendue : "test@test.com test"
            Utilisateur test_user_db = userDao.lire(user_id);
            System.out.println(test_user_db.getEmail() + " " + test_user_db.getMotDePasse());

        //resultat attendue : test_changement
            test_user_db.setMotDePasse("test_changement");
            userDao.mettreAJour(test_user_db);
            test_user_db = userDao.lire(user_id);
            System.out.println(test_user_db.getMotDePasse());

        //resultat attendue : null
            userDao.supprimer(user_id);
            test_user_db = userDao.lire(user_id);
            System.out.println(test_user_db);

    //test pour LieuRecharge
            lieuDao.creer(lieu);
            Long lieu_id = lieu.getId();
            LieuRecharge test_lieu_db = lieuDao.lire(lieu_id);
            System.out.println(test_lieu_db.getNom() + " " + test_lieu_db.getAdresse());
            test_lieu_db.setNom("test_changement");
            lieuDao.mettreAJour(test_lieu_db);
            test_lieu_db = lieuDao.lire(lieu_id);
            System.out.println(test_lieu_db.getNom());
            lieuDao.supprimer(lieu_id);
            test_lieu_db = lieuDao.lire(lieu_id);
            System.out.println(test_lieu_db);

    //test pour BorneRecharge
            borneDao.creer(borne);
            Long borne_id = borne.getId();
            BorneRecharge test_borne_db = borneDao.lire(borne_id);

            System.out.println(test_borne_db.getEtat() + " " + test_borne_db.getTarifHoraire());
            test_borne_db.setEtat(EtatBorne.RESERVEE);
            borneDao.mettreAJour(test_borne_db);
            test_borne_db = borneDao.lire(borne_id);
            System.out.println(test_borne_db.getEtat());
            borneDao.supprimer(borne_id);
            test_borne_db = borneDao.lire(borne_id);
            System.out.println(test_borne_db);

    //test pour Reservation
            reservationDao.creer(reservation);
            Long reservation_id = reservation.getId();
            Reservation test_reservation_db = reservationDao.lire(reservation_id);
            System.out.println(test_reservation_db.getDateDebut() + " " + test_reservation_db.getDateFin());
            cal.set(2025, Calendar.APRIL, 27, 9, 0, 0);
            test_reservation_db.setDateFin(cal.getTime());
            reservationDao.mettreAJour(test_reservation_db);
            test_reservation_db = reservationDao.lire(reservation_id);
            System.out.println(test_reservation_db.getDateFin());
            reservationDao.supprimer(reservation_id);
            test_reservation_db = reservationDao.lire(reservation_id);
            System.out.println(test_reservation_db);
    }
}


        //resultat attendue :
        //!!fonction adapter pour faciliter les tests ; version de base disponible sans les arguments!!
