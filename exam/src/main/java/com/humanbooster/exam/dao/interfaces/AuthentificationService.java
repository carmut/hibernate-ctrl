package com.humanbooster.exam.dao.interfaces;

public interface AuthentificationService {

    /**
     * Création d'un compte
     */
    public void enregistrer();

    /**
     * Connexion a un compte
     * @param email email du compte
     * @param motDePasse mot de passe du compte
     */
    public void connection(String email, String motDePasse);

    /**
     * Vérifie si le code donné est bon et passe le compte en valide si c'est le cas
     * @param code code de validation du compte
     */
    public void validation(int code);

    /**
     * Génere un code de validation pour le compte
     * entre 1000 et 9999 inclus
     */
    public void generateCodeValidation();
}
