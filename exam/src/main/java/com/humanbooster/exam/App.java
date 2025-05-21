package com.humanbooster.exam;

import com.humanbooster.exam.dao.UtilisateurDao;
import com.humanbooster.exam.model.Utilisateur;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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


    //test pour l'utilisateur
        Utilisateur user = new Utilisateur();

        //infos user
        String user_email = "test@test.com";
        String user_motDePasse = "test";
        int user_code;

        //resultat attendue : faux email avec code validation
        //!!fonction adapter pour faciliter les tests ; version de base disponible sans les arguments!!
        user_code = user.enregistrer_test(user_email, user_motDePasse);
        //resultat attendue : validation effectuer avec succee
        user.validation(user_code);
        //resultat attendue : connection réussi
        user.connection(user_email, user_motDePasse);

    //test pour utilisateurDao
        UtilisateurDao userDao = new UtilisateurDao(sessionFactory);
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
        //infos LieuRecharge

        //resultat attendue :
    }
}


        //resultat attendue :
        //!!fonction adapter pour faciliter les tests ; version de base disponible sans les arguments!!
