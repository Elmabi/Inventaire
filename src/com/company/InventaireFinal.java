/**
 * Ce programme permet la gestion d'un inventaire de vêtements.
 * Il permet :
 * - D'ajouter des vêtements dans l'inventaire
 * - De modifier l'attribut des vêtements
 * - De modifier les attributs d'un fournisseur
 * - De supprimer un vêtement
 * - D'afficher l'état de l'inventaire
 * - De lister les vêtements actif ou périmé de l'inventaire
 * - De lister l'état de l'inventaire en fonction du sexe
 * - D'afficher la valeur totale de l'inventaire en fonction du Prix de vente et Prix de revient
 * <p>
 * <p>
 * Version 3.00, @author Armel Franck Djiongo
 */

package com.company;

import iofichiertab.IOFichierTab;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class InventaireFinal {


    //<editor-fold desc="CONSTANTES">
    static final int longueurTableau = 22;  // la longueur des tableaux
    // Pour colorier certains caractères
    static final String COULEUR_GREEN = "\u001B[32m";
    static final String COULEUR_RESET = "\u001B[0m";
    static final String COULEUR_RED = "\u001B[31m";
    static final String COULEUR_BLACK_BACKGROUND = "\033[0;107m";
    static final String COULEUR_BLUE = "\u001B[34m";
    static final String COULEUR_CYAN = "\u001B[36m";
    static final String COULEUR_YELLOW = "\u001B[33m";
    static final String COULEUR_BOLD_BRIGHT = "\033[1;94m";
    static final String COULEUR_CYAN_BRIGHT = "\033[0;96m";
    static final String COULEUR_BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    //le numéro de téléphone de l'entreprise
    static final String NUMERO_TELEPHONE_ENTREPRISE = "405-895-854";
    //Certains messages qui s'affichent s'il y'a une erreur
    static final String MESSAGE_SORTIR_DU_MENU = COULEUR_RED + "(Tapez entrez) Pour sortir: \n" + COULEUR_RESET;
    static final String MESSAGE_SAISI_INEXISTANT = COULEUR_RED + "Entrez le chiffre correspondant. Merci!!! " + COULEUR_RESET;
    static final String MESSAGE_SAUVEGARDE_ECHEC = COULEUR_RED + "Comme vous êtes sorti, rien a été sauvegardé." + COULEUR_RESET;
    static final String MESSAGE_SAUVEGARDE_REUSSI = COULEUR_BLUE + "Yeahh, Sauvegarde réussi!" + COULEUR_RESET;
    static final String MESSAGE_NUMERO_EXISTANT = COULEUR_RED + "Ce numéro a été déjà affecté à un autre vêtement." +
            " Entrez un nouveau numéro. Merci!" + COULEUR_RESET;
    static final String MESSAGE_NUMERO_INEXISTANT = COULEUR_RED + "Le code n'existe pas. Entrez un code valide. Merci!\n" + COULEUR_RESET;
    static final String MESSAGE_ITEM_SUPPRIME = COULEUR_RED + " a été retiré de l'inventaire!\n " + COULEUR_RESET;
    static final String MESSAGE_ITEM_INACTIF = COULEUR_RED + " est maintenant inactif\n" + COULEUR_RESET;
    static final String MESSAGE_STATUT_MODIFIE = COULEUR_BLUE + "Yeah, STATUT MODIFIE!" + COULEUR_RESET;
    static final String MESSAGE_QUANTITE_MODIFIE = COULEUR_BLUE + "Yeah, nouvelle quantité modifié!" + COULEUR_RESET;
    static final String MESSAGE_PRIXDEVENTE_MODIFIE = COULEUR_BLUE + "Yeahh, PRIX DE VENTE MODIFIE!" + COULEUR_RESET;
    static final String MESSAGE_PRIXDEREVIENT_MODIFIE = COULEUR_BLUE + "Yeahh, PRIX DE REVIENT MODIFIE!" + COULEUR_RESET;
    static final String MESSAGE_ARTICLE_MODIFIE = COULEUR_BLUE + "Yeahh, l'article a été modifié avec success!".toUpperCase()
            + COULEUR_RESET;
    static final String MESSAGE_TRAI_UNION = "\n_____________________________________________________________________" +
            "______";
    static final String MESSAGE_TRAITEMENT_QUANTITE = COULEUR_RED + "La quantité ou le seuil minimale ne peuvent être " +
            "négatifs et ne peuvent contenir que des chiffres. Merci!" + COULEUR_RESET;
    static final String MESSAGE_TRAITEMENT_CODE = COULEUR_RED + "N'entrez que des chiffres s'il vous plait. " +
            "Merci! " + COULEUR_RESET;
    static final String MESSAGE_TRAITEMENT_CODE_IDENTIFIANT = COULEUR_RED + "N'entrez que des chiffres au niveau du " +
            "code d'identifiant du fabriquant s'il vous plait. Merci! " + COULEUR_RESET;
    static final String MESSAGE_PROGRAMME_FERME = COULEUR_CYAN + ("************ Programme fermé avec " +
            "succès *****************").toUpperCase()
            + COULEUR_RESET;
    static final String MESSAGE_PROVENANCE_MODIFIE = COULEUR_BLUE + "Yeahh, PROVENANCE MODIFIE!" + COULEUR_RESET;
    static final String MESSAGE_MENU_AJOUTER_VETEMENT = COULEUR_CYAN + "************ MENU AJOUTER UN VÊTEMENT ************"
            + COULEUR_RESET;
    static final String MESSAGE_MENU_MODIFIER_ATTRIBUT_VÊTEMENT = COULEUR_CYAN + "\n************ MENU MODIFIER ATTRIBUT" +
            " VÊTEMENT ************" + COULEUR_RESET;
    static final String MESSAGE_MENU_MODIFIER_ATTRIBUT_FOURNISSEUR = COULEUR_CYAN + "\n************ MENU MODIFIER ATTRIBUT" +
            " FOURNISSEUR ************" + COULEUR_RESET;
    static final String MESSAGE_MENU_SUPPRIMER_VÊTEMENT = COULEUR_CYAN + "\n************ MENU SUPPRIMER ATTRIBUT VÊTEMENT " +
            "************" + COULEUR_RESET;
    static final String MESSAGE_MENU_LISTER_INVENTAIRE = COULEUR_CYAN + "\n************ MENU LISTER INVENTAIRE " +
            "************" + COULEUR_RESET;
    static final String MESSAGE_MENU_OBTENIR_VALEUR_INVENTAIRE = COULEUR_CYAN + "\n************ MENU OBTENIR VALEUR INVENTAIRE " +
            "************" + COULEUR_RESET;
    static final String MESSAGE_SORTIR_DU_MENU_VALIDE = COULEUR_CYAN + "\nVOUS VENEZ DE SORTIR DU " + COULEUR_RESET;
    // </editor-folder> CONSTANTES

    //<editor-fold desc="VARIABLES">
    static int compteur = 0;      // le compteur pour connaitre le nombre de vêtements actuel dans l'inventaire
    static int compteurFournisseur = 0; // compteur pour le tableau fournisseur
    static int[] tableauNumeroVetement = new int[longueurTableau];       // Tableau numéro du vêtement
    static int[] tableauQuantite = new int[longueurTableau]; // Tableau quantité
    static int[] tableauQuantiteMinimum = new int[longueurTableau];  // Tableau quantité minimum
    static float[] tableauPrixDeRevient = new float[longueurTableau];   // Tableau Prix de revient pour chaque item
    static float[] tableauPrixDeVente = new float[longueurTableau];  // Tableau prix de vente pour chaque item
    static float[] tableauValeurTotalPV = new float[longueurTableau]; // Tableau Valeur totale en fonction du PV
    static String[] tableauDescription = new String[longueurTableau]; // Tableau Descriptif
    static String[] tableauSexe = new String[longueurTableau];    // Tableau du sexe
    static String[] tableauOrigine = new String[longueurTableau]; // Tableau Origine de l'article
    static String[] tableauIdentifiantFournisseur = new String[longueurTableau]; // Tableau identifiant du fournisseur
    static String[] tableauIdentifiantFournisseur2 = new String[longueurTableau]; // 2eme Tableau identifiant du fournisseur
    static String[] tableauTelephoneFournisseur = new String[longueurTableau]; //Tableau pour le numéro de telephone des fournisseurs.
    static String[] tableauTelephoneFournisseur2 = new String[longueurTableau]; // 2eme Tableau pour le numéro de telephone des fournisseurs.
    static String[] tableauFournisseur = new String[longueurTableau]; //Tableau pour la liste des fournisseurs.
    static String[] tableauStatut = new String[longueurTableau];  // Tableau Statut du vêtement Actif ou Périmé
    static boolean[] tableauStatutEnBoolean = new boolean[longueurTableau]; // Deuxième Tableau statut de vêtement
    // en boolean. Sert de reference
    // </editor-folder> VARIABLE

    public static void main(String[] args) {

        boolean programmeEstFermé = false;  // aide a fermer le programme si 0 est saisi

        //<editor-fold desc="CONSERVATION DE DONNEE">
        final String SEPARATEUR = ";"; //Séparateur des champs de le fichier
        String fichier = "dataTableaux.txt"; //nom du fichier de données
        String fichierFournisseur = "TableauFournisseur.txt";
        if (new File(fichier).exists()) {
            compteur = IOFichierTab.lireFichier(fichier, SEPARATEUR, tableauNumeroVetement, tableauDescription,
                    tableauSexe, tableauQuantite, tableauQuantiteMinimum, tableauOrigine,
                    tableauPrixDeRevient, tableauPrixDeVente, tableauStatut, tableauStatutEnBoolean,
                    tableauTelephoneFournisseur, tableauIdentifiantFournisseur);
            compteurFournisseur = IOFichierTab.lireFichier(fichierFournisseur, SEPARATEUR, tableauFournisseur,
                    tableauIdentifiantFournisseur2, tableauTelephoneFournisseur2);
        }
        // </editor-folder>

        //******************************************************
        //Dans cette boucle WHILE, tout se passe. Elle contient 6 cas de switch qui permettent de naviguer dans l'inventaire.
        //Tant que programmeEstFermé est false, le programme reste ouvert sinon si 0 est saisi le programme se ferme.
        //Le cas 1 Pour ajouter un vêtement
        //Le cas 2 Pour modifier les attributs d'un vêtements
        //Le cas 3 Pour modifier les attributs d'un fournisseur
        //Le cas 4 Pour supprimer un vêtement
        //Le cas 5 Pour liste l'inventaire
        //Le cas 6 Pour obtenir la valeur totale de l'inventaire
        //******************************************************/
        while (!programmeEstFermé) {
            String choixMenu = choixAfficherMenu(); // variable du choix de menu du programme par l'utilisateur
            switch (choixMenu) {
                //"CAS 1 POUR AJOUTER UN VÊTEMENT"
                case "1":
                    menuAjouter();
                    break;
                //CAS 2 POUR MODIFIER CERTAINS ATTRIBUTS DE L'ARTICLE.">
                case "2":
                    menuModifierAttributItem();
                    break;
                //CAS 3 POUR MODIFIER LES ATTRIBUTS DU FOURNISSEUR">
                case "3":
                    menuModifierAttributFournisseur();
                    break;
                //CAS 4 POUR SUPPRIMER UN ARTICLE DE L'INVENTAIRE.">
                case "4":
                    menuSupprimerVetement();
                    break;
                //CAS 5 POUR AFFICHER L'INVENTAIRE.">
                case "5":
                    menuInventaire();
                    break;
                //CAS 6 LA VALEUR TOTALE DE L'INVENTAIRE">
                case "6":
                    menuValeurTotaleInventaire();
                    break;
                //CAS 0 LE PROGRAMME SE FERME">
                default:
                    programmeEstFermé = programmeEstFerme(choixMenu);
                    break;
            } // fin switch Choixmenu principale
        } // fin boucle while

        //Fonction pour sauvegarder les données entrées par l'utilisateur dans un fichier
        IOFichierTab.ecrireFichier(fichier, SEPARATEUR, compteur, tableauNumeroVetement, tableauDescription, tableauSexe,
                tableauQuantite, tableauQuantiteMinimum, tableauOrigine, tableauPrixDeRevient, tableauPrixDeVente,
                tableauStatut, tableauStatutEnBoolean, tableauTelephoneFournisseur, tableauIdentifiantFournisseur);
        IOFichierTab.ecrireFichier(fichierFournisseur, SEPARATEUR, compteurFournisseur, tableauFournisseur,
                tableauIdentifiantFournisseur2, tableauTelephoneFournisseur2);
    } //fin main

    //<editor-fold desc="MES MÉTHODES.">

    // Methode pour valider les chiffres réels.
    public static boolean estReel(String mot, String messageErreur) {

        // On considère que le code entré dès le depart ne contient que des chiffres.
        for (int i = 0; i < mot.length(); i++) {
            if ((!Character.isDigit(mot.charAt(i)))) {
                System.out.println(messageErreur);
                return false;
            }
        }
        return true;
    }

    // Permet d'afficher l'information que représente le code entré par l'utilisateur
    public static void afficherInformationItemChoisi(int indexNumeroTrouve, String[] tableau, String information) {
        System.out.println("Le code que vous venez d'entrer correspond " + information + COULEUR_BLUE
                + tableau[indexNumeroTrouve].toUpperCase() + COULEUR_RESET);
    }

    //*****************************************************
    //Affiche le menu pour supprimer un vêtement à partir du numéro du vêtement. Si le numéro est localisé, la procédure
    //supprimerVetement() est enclenché.
    //*****************************************************
    public static void menuSupprimerVetement() {
        listerInventaire();
        afficherMessage(MESSAGE_MENU_SUPPRIMER_VÊTEMENT);
        String numeroVetement = entreeNumeroVetement();
        if (numeroVetement.isEmpty()) { // Si 0 est saisi, ce menu se ferme
            afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU_SUPPRIMER_VÊTEMENT");
            return;
        }
        if (!numéroVêtementExiste(numeroVetement)) {
            afficherMessage(MESSAGE_NUMERO_INEXISTANT);
            menuSupprimerVetement();
            return;
        }
        int indexNumeroTrouve = localiser(numeroVetement, tableauNumeroVetement, compteur);
        supprimerVetement(indexNumeroTrouve);
    }

    //*****************************************************
    //Permet de supprimer un vêtement. (0) pour retirer de l'inventaire et (1) pour rendre inactif
    //*****************************************************
    public static void supprimerVetement(int indexNumeroTrouve) {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        afficherMessage(MESSAGE_MENU_SUPPRIMER_VÊTEMENT);
        afficherInventaire(indexNumeroTrouve);
        System.out.print("\nVoulez-vous le retirer de l'inventaire (0) ou rendre inactif (1) ? \n" + MESSAGE_SORTIR_DU_MENU);
        String input = clavier.nextLine(); // Pour contenir la saisi
        switch (input) {
            case "":
                afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU\n");
                menuSupprimerVetement();
                return;
            case "0":
                retirerVetement(indexNumeroTrouve);
                break;
            case "1":
                rendreInactif(indexNumeroTrouve);
                break;
            default:
                afficherMessage(MESSAGE_SAISI_INEXISTANT);
                supprimerVetement(indexNumeroTrouve);
                break;
        }
    }

    //*****************************************************
    //Permet de rendre un vêtement inactif.
    //*****************************************************
    private static void rendreInactif(int indexNumeroTrouve) {
        for (int t = indexNumeroTrouve; t < compteur; t++) {
            tableauStatutEnBoolean[t] = false;
            tableauStatut[t] = "perime";
        }
        afficherMessage(COULEUR_BLUE + tableauDescription[indexNumeroTrouve].toUpperCase()
                + COULEUR_RESET + MESSAGE_ITEM_INACTIF);
    }

    //*****************************************************
    //Permet de retirer un vêtement de l'inventaire.
    //*****************************************************
    private static void retirerVetement(int indexNumeroTrouve) {
        afficherMessage(COULEUR_BLUE + tableauDescription[indexNumeroTrouve].toUpperCase()
                + COULEUR_RESET + MESSAGE_ITEM_SUPPRIME);
        for (int t = indexNumeroTrouve; t < compteur - 1; t++) {
            tableauNumeroVetement[t] = tableauNumeroVetement[t + 1];
            tableauDescription[t] = tableauDescription[t + 1];
            tableauSexe[t] = tableauSexe[t + 1];
            tableauQuantite[t] = tableauQuantite[t + 1];
            tableauQuantiteMinimum[t] = tableauQuantiteMinimum[t + 1];
            tableauOrigine[t] = tableauOrigine[t + 1];
            tableauPrixDeRevient[t] = tableauPrixDeRevient[t + 1];
            tableauPrixDeVente[t] = tableauPrixDeVente[t + 1];
            tableauStatut[t] = tableauStatut[t + 1];
            tableauStatutEnBoolean[t] = tableauStatutEnBoolean[t + 1];
            tableauIdentifiantFournisseur[t] = tableauIdentifiantFournisseur[t + 1];
            tableauTelephoneFournisseur[t] = tableauTelephoneFournisseur[t + 1];
        }
        compteur--;
    }

    //*****************************************************
    //Présente le menu pour modifier l'attribut d'un vêtement à partir du code du vêtement. Si le code existe, la
    //procédure modifierAttributItem() est enclenché.
    //*****************************************************
    private static void menuModifierAttributItem() {
        listerInventaire();
        afficherMessage(MESSAGE_MENU_MODIFIER_ATTRIBUT_VÊTEMENT);
        String numeroVetement = entreeNumeroVetement();
        if (numeroVetement.isEmpty()) { //le menu se ferme
            afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU_MODIFIER_ATTRIBUT_VÊTEMENT");
            return;
        }
        if (!numéroVêtementExiste(numeroVetement)) {// Si le code du vêtement est invalide
            afficherMessage(MESSAGE_NUMERO_INEXISTANT);
            menuModifierAttributItem();
            return;
        }
        int indexNumeroTrouve = localiser(numeroVetement, tableauNumeroVetement, compteur);
        modifierAttributItem(indexNumeroTrouve);
    }

    //*****************************************************
    //Permet de modifier l'attribut d'un vêtement à partir du code du vêtement. Modifie notamment la quantité, le prix de
    //revient, le prix de vente, le statut et la provenance.
    //*****************************************************
    public static void modifierAttributItem(int indexNumeroTrouve) {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        afficherMessage(MESSAGE_MENU_MODIFIER_ATTRIBUT_VÊTEMENT);
        afficherInventaire(indexNumeroTrouve);

        System.out.print("\nVoulez vous modifier :\n(1) Sa quantité\n(2) Son prix de revient\n" +
                "(3) Son prix de vente\n(4) Son statut\n(5) Sa provenance\n(6) Ou tout modifier\n" + MESSAGE_SORTIR_DU_MENU);
        String input = clavier.nextLine();
        switch (input) {
            case "":
                afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU\n");
                menuModifierAttributItem();
                return;
            // Pour modifier juste la quantité de l'article.
            case "1":
                modifierQuantite(indexNumeroTrouve);
                modifierAttributItem(indexNumeroTrouve);
                break;
            // Pour modifier juste le prix de revient de l'article
            case "2":
                modifierPrixDeRevient(indexNumeroTrouve);
                modifierAttributItem(indexNumeroTrouve);
                break;
            // Pour modifier juste le prix de vente de l'article
            case "3":
                modifierPrixDeVente(indexNumeroTrouve);
                modifierAttributItem(indexNumeroTrouve);
                break;
            // Pour modifier juste le statut de l'article
            case "4":
                modifierStatut(indexNumeroTrouve);
                modifierAttributItem(indexNumeroTrouve);
                break;
            // Pour modifier la provenance du vêtement et le numéro et téléphone du fournisseur.
            case "5":
                modifierProvenance(indexNumeroTrouve);
                modifierAttributItem(indexNumeroTrouve);
                break;
            // Pour tout modifier de l'article
            case "6":
                modifierQuantite(indexNumeroTrouve);
                modifierPrixDeRevient(indexNumeroTrouve);
                modifierPrixDeVente(indexNumeroTrouve);
                modifierStatut(indexNumeroTrouve);
                afficherMessage(MESSAGE_ARTICLE_MODIFIE);
                modifierAttributItem(indexNumeroTrouve);
                break;
            // Quand le mauvais chiffre du menu "modifier l'attribut d'un article" est saisi
            default:
                System.out.println("\n" + MESSAGE_SAISI_INEXISTANT);
                modifierAttributItem(indexNumeroTrouve);
        }

    }

    //*****************************************************
    //Permet de modifier le statut du vêtement.
    //*****************************************************
    private static void modifierStatut(int indexNumeroTrouve) {
        String etatItem = entreeStatut();
        if (!etatItem.isEmpty()) {
            sauvegarderEtatItem(etatItem, indexNumeroTrouve);
            afficherMessage(MESSAGE_STATUT_MODIFIE);
        }
    }

    //*****************************************************
    //Permet de modifier le prix de vente du vêtement.
    //*****************************************************
    private static void modifierPrixDeVente(int indexNumeroTrouve) {
        String newPrixDeVente = entreePrixDeVente();
        if (!newPrixDeVente.isEmpty()) {
            tableauPrixDeVente[indexNumeroTrouve] = Float.parseFloat(newPrixDeVente.replace(",", "."));
            afficherMessage(MESSAGE_PRIXDEVENTE_MODIFIE);
        }
    }

    //*****************************************************
    //Permet de modifier le prix de revient  du vêtement.
    //*****************************************************
    private static void modifierPrixDeRevient(int indexNumeroTrouve) {
        String newPrixDeRevient = entreePrixDeRevient();
        if (!newPrixDeRevient.isEmpty()) {
            tableauPrixDeRevient[indexNumeroTrouve] = Float.parseFloat(newPrixDeRevient.replace(",", "."));
            afficherMessage(MESSAGE_PRIXDEREVIENT_MODIFIE);
        }
    }

    //*****************************************************
    //Permet de modifier la quantité du vêtement.
    //*****************************************************
    private static void modifierQuantite(int indexNumeroTrouve) {
        String newQuantite = entreeQuantite();
        if (!newQuantite.isEmpty()) {
            tableauQuantite[indexNumeroTrouve] = Integer.parseInt(newQuantite);
            afficherMessage(MESSAGE_QUANTITE_MODIFIE);
        }
    }

    //*****************************************************
    //Permet de modifier la provenance de l'item. Notamment le nom du fabriquant, son identifiant, son numéro de téléphone.
    //*****************************************************
    private static void modifierProvenance(int indexNumeroTrouve) {
        String[] informationFournisseur = entreeOrigine();
        String identifiantOrigine = informationFournisseur[0];
        String fabriquant = informationFournisseur[1];
        String numeroFabriquant = informationFournisseur[2];
        sauvegarderOrigineVêtement(numeroFabriquant, fabriquant, identifiantOrigine, indexNumeroTrouve);
        sauvegarderFournisseur(numeroFabriquant, fabriquant, identifiantOrigine, compteurFournisseur);
        afficherMessage(MESSAGE_PROVENANCE_MODIFIE);
    }

    //*****************************************************
    //Permet d'afficher le Menu pour lister l'inventaire. Dépendamment du choix de l'utilisateur la procédure de la
    // demandée est enclenchée.
    //*****************************************************
    private static void menuInventaire() {
        boolean menuEstFermé;
        do {
            afficherMessage(MESSAGE_MENU_LISTER_INVENTAIRE);
            String choix = choixMenuInventaire();  //choix saisi par l'utilisateur
            menuEstFermé = false;
            switch (choix) {
                case "":
                    menuEstFermé = true;
                    afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU_LISTER_INVENTAIRE");
                    break;
                // (1) Pour lister l'inventaire.
                case "1":
                    listerInventaire();
                    break;
                //(2) La liste des vêtements actifs.
                case "2":
                    listerActifouInactif(tableauStatutEnBoolean, true);
                    break;
                // (3) La liste des vêtements périmé.
                case "3":
                    listerActifouInactif(tableauStatutEnBoolean, false);
                    break;
                // La liste des sexes.
                case "4":
                    menuSexe();
                    break;
                // La liste des vêtements dont la quantité est moins du seuil minimale.
                case "5":
                    listerQuantiteMinimale();
                    break;
                // la liste des vêtements fabrique pas nous ou les autres
                case "6":
                    menuOrigineItem();
                    break;
                // La liste des fournisseurs
                case "7":
                    listerFournisseur();
                    break;
                // La liste des vêtements en fonction du fournisseur.
                case "8":
                    listerItemEnFonctionDuFournisseur(tableauIdentifiantFournisseur);
                    break;
                default:
                    afficherMessage(MESSAGE_SAISI_INEXISTANT);
            }
        }
        while (!menuEstFermé);
    }

    //*****************************************************
    //Permet d'afficher le Menu de la liste de la valeur totale de l'inventaire en fonction du Prix de vente et de revient.
    //*****************************************************
    private static void menuValeurTotaleInventaire() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        boolean menuEstFermé;
        do {
            afficherMessage(MESSAGE_MENU_OBTENIR_VALEUR_INVENTAIRE);
            menuEstFermé = false;
            System.out.print("\n Voulez-vous affichez la valeur totale : \n(1) En fonction du PRIX DE VENTE : " +
                    "\n(2) En fonction du PRIX DE REVIENT :\n" + MESSAGE_SORTIR_DU_MENU);
            String choix = clavier.nextLine();
            switch (choix) {
                case "":
                    menuEstFermé = true;
                    afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU_OBTENIR_VALEUR_INVENTAIRE");
                    break;
                case "1":
                    listerValeurTotaleInventaire(tableauPrixDeVente, "prix de vente");
                    break;
                case "2":
                    listerValeurTotaleInventaire(tableauPrixDeRevient, "prix de revient");
                    break;
                default:
                    afficherMessage(MESSAGE_SAISI_INEXISTANT);
                    break;
            }
        }
        while (!menuEstFermé);
    }

    //*****************************************************
    //Permet d'afficher le menu pour lister les vêtements en fonction de leur origine soit fabriqué par l'entreprise ou
    //fabriqué par les fournisseurs. Quand le choix est entré, les procédures qui en découlent sont enclenchées.
    //*****************************************************
    private static void menuOrigineItem() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        boolean menuEstFermé;
        do {
            menuEstFermé = false;
            System.out.print("\nFabriqué par nous (1) ou les autres (2) :\n" + MESSAGE_SORTIR_DU_MENU);
            String choix = clavier.nextLine();
            switch (choix) {
                case "":
                    menuEstFermé = true;
                    break;
                case "1":
                    listerEnFonctionOrigine(1, "vous");
                    break;
                case "2":
                    listerEnFonctionOrigine(2, "vos fournisseurs");
                    break;
                default:
                    System.out.println(MESSAGE_SAISI_INEXISTANT + "\n");
                    break;
            }
        }
        while (!menuEstFermé);
    }

    //*****************************************************
    //Permet d'afficher le menu pour lister les vêtements en fonction du sexe.
    //*****************************************************
    private static void menuSexe() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        boolean menuEstFermé;
        do {
            menuEstFermé = false;
            System.out.print("\nQuel sexe voulez-vous afficher ? \n(1) Hommes ?\n(2) Femmes ? " +
                    "\n(3) Unisexes ?\n" + MESSAGE_SORTIR_DU_MENU);
            String choix = clavier.nextLine();
            switch (choix) {
                case "":
                    menuEstFermé = true;
                    break;
                //Au cas ou le sexe HOMME est choisi.
                case "1":
                    listerSexe("Homme");
                    break;
                //Au cas ou le sexe FEMME est choisi.
                case "2":
                    listerSexe("femme");
                    break;
                //Au cas ou le sexe unisexe est choisi.
                case "3":
                    listerSexe("unisexe");
                    break;
                // Au cas ou le chiffre correspondant du sexe est mal saisi.
                default:
                    System.out.println("\n" + MESSAGE_SAISI_INEXISTANT);
            }
        }
        while (!menuEstFermé);
    }

    //*****************************************************
    //Permet de lister les vêtements en fonction de leur Origine. Cet procédure est utilisée dans la procédure
    // menuOrigineItem(). À noter que l'identifiant de l'entreprise en tant que fabriquant d'un vêtement est 1.
    //*****************************************************
    private static void listerEnFonctionOrigine(int choixOrigine, String origine) {
        int itemCompté = 0;
        for (int i = 0; i < compteur; i++) {
            boolean origineEstFournisseur = (Integer.parseInt(tableauIdentifiantFournisseur[i]) > 1);
            boolean origineEstEntreprise = (1 == Integer.parseInt(tableauIdentifiantFournisseur[i]));
            switch (choixOrigine) {
                case 1:
                    if (origineEstEntreprise) {
                        afficherInventaire(i);
                        itemCompté = itemCompté + 1;
                    }
                    break;
                case 2:
                    if (origineEstFournisseur) {
                        afficherInventaire(i);
                        itemCompté = itemCompté + 1;
                    }
                    break;
            }
        }
        System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement vous avez " + (itemCompté) + " item(s) fabriqué(s) par "
                + origine.toUpperCase() + " dans votre inventaire\n" + COULEUR_RESET);
    }

    //*****************************************************
    //Permet de lister les vêtements dont la quantité est moins que le seuil minimale.
    //*****************************************************
    private static void listerQuantiteMinimale() {
        int itemCompte = 0;
        for (int i = 0; i < compteur; i++) {
            if (tableauQuantite[i] < tableauQuantiteMinimum[i]) {
                afficherInventaire(i);
                itemCompte = itemCompte + 1;
            }
        }
        System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement vous avez " + (itemCompte) + " items dont la quantite est" +
                " MOINS que le SEUIL MINIMALE dans votre inventaire\n" + COULEUR_RESET);
    }

    //*****************************************************
    //Permet de lister la valeur totale de l'inventaire. Elle est utilisé dans la procédure menuValeurTotaleInventaire().
    //*****************************************************
    public static void listerValeurTotaleInventaire(float[] tableau, String chaine) {
        double valeurTotaleInventaire = 0;
        for (int i = 0; i < compteur; i++) {
            tableauValeurTotalPV[i] = tableau[i] * tableauQuantite[i];
            valeurTotaleInventaire += tableauValeurTotalPV[i];
        }
        System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement vous avez " + (compteur) + " items dans votre inventaire et ");
        System.out.print("La valeur totale de votre inventaire en fonction du " + chaine.toUpperCase()
                + " est de $");
        System.out.format(Locale.CANADA, "%.2f", valeurTotaleInventaire);
        System.out.println(COULEUR_RESET);
    }

    //*****************************************************
    //Permet de lister les vêtements en fonction du sexe. Elle est utilisée dans la procédure menuSexe().
    //*****************************************************
    private static void listerSexe(String sexe) {
        int itemCompte = 0;
        for (int i = 0; i < compteur; i++) {
            if (sexe.equalsIgnoreCase(tableauSexe[i])) {
                afficherInventaire(i);
                itemCompte = itemCompte + 1;
            }
        }
        System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement vous avez " + (itemCompte) + " items " + sexe.toUpperCase()
                + " dans votre inventaire\n" + COULEUR_RESET);
    }

    //*****************************************************
    //Fonction qui affiche les choix possible pour le Menu Inventaire et capture le choix saisi par l'utilisateur.
    //Elle est utilisée dans procédure menuInventaire().
    //*****************************************************
    public static String choixMenuInventaire() {
        System.out.print(COULEUR_BOLD_BRIGHT + "\nVoulez vous :\n(1) Lister l'inventaire ? \n(2) La liste des vêtements actifs ? " +
                "\n(3) La liste des vêtements périmé ? \n(4) La liste des vêtements par sexe ? " +
                "\n(5) La liste des items dont la qte est moins du seuil minimal ?" +
                "\n(6) La liste des vêtements fabrique par nous ou les autres ? \n(7) La liste des fournisseurs ? " +
                "\n(8) La liste des vêtements achetés auprès d'un fournisseur en particulier ?\n" + COULEUR_RESET
                + MESSAGE_SORTIR_DU_MENU);
        Scanner clavier = new Scanner(System.in); // Scanner pour prendre la saisi de l'utilisateur
        return clavier.nextLine();
    }

    //*****************************************************
    //Permet de lister les vêtements actif ou inactif. Elle est utilisée dans procédure menuInventaire().
    //*****************************************************
    private static void listerActifouInactif(boolean[] tab, boolean estActif) {
        int itemCount = 0;
        String etatStatut = " ";
        for (int i = 0; i < compteur; i++) {
            if (tab[i] == estActif) {
                afficherInventaire(i);
                itemCount = itemCount + 1;
                etatStatut = tableauStatut[i];
            }
        }
        System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement vous avez " + itemCount + " items " + etatStatut.toUpperCase()
                + " dans votre inventaire\n" + COULEUR_RESET);
    }

    //*****************************************************
    //Permet de lister l'inventaire total. Elle est utilisée dans procédure menuInventaire().
    //*****************************************************
    private static void listerInventaire() {
        for (int i = 0; i < compteur; i++) {
            afficherInventaire(i);
        }
        if (longueurTableau == compteur) {//Au cas ou l'inventaire est plein.
            System.out.println(COULEUR_CYAN_BRIGHT + "Actuellement vous avez " + (compteur) + " items dans votre inventaire" +
                    " et il est plein.  \nVous pouvez contacter votre développeur pour qu'il augmente la longueur des " +
                    "vêtements que votre inventaire peut contenir.\n"
                    + COULEUR_RESET);
        } else {
            System.out.println(COULEUR_CYAN_BRIGHT + "Actuellement vous avez " + (compteur) + " items dans votre inventaire" +
                    " et il vous reste " + (longueurTableau - compteur) + " vêtements avant que votre inventaire soit plein.\n"
                    + COULEUR_RESET);
        }
    }

    //*****************************************************
    //Permet de formater l'affichage de l'inventaire. Elle sera utilisé parfois dans les Procédures qui listent les
    //vêtements.
    //*****************************************************
    public static void afficherInventaire(int i) {
        System.out.println(COULEUR_BLACK_BACKGROUND_BRIGHT + COULEUR_BOLD_BRIGHT + tableauNumeroVetement[i] + "\tDESCRIPTION : "
                + tableauDescription[i] + COULEUR_RESET + "\n" + "\tSEXE : " + tableauSexe[i] + " | " + COULEUR_GREEN + "QUANTITE : "
                + tableauQuantite[i] + COULEUR_RESET + " | " + COULEUR_RED + "QUANTITE MIN : " + tableauQuantiteMinimum[i] + COULEUR_RESET
                + "\n" + COULEUR_BLUE + "\tORIGINE : " + tableauOrigine[i] + COULEUR_RESET + " | " + "PRIX DE REVIENT : $"
                + tableauPrixDeRevient[i] + "\n" + "\tPRIX DE VENTE : $"
                + tableauPrixDeVente[i] + " | " + COULEUR_YELLOW + "STATUT : " + tableauStatut[i] + COULEUR_RESET + " | "
                + "IDENTIFIANT FOURNISSEUR : " + tableauIdentifiantFournisseur[i] + MESSAGE_TRAI_UNION);
    }

    //*****************************************************
    //Menu permettant d'afficher la session pour modifier les attributs d'un fournisseur.
    //*****************************************************
    public static void menuModifierAttributFournisseur() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        boolean menuEstFermé;
        do {
            listerFournisseur();
            afficherMessage(MESSAGE_MENU_MODIFIER_ATTRIBUT_FOURNISSEUR);
            menuEstFermé = false;
            System.out.print("\nQuel fournisseur voulez-vous modifier ses attributs," +
                    "\nentrez son code d'identifiant ou " + MESSAGE_SORTIR_DU_MENU);
            String identifiantFournisseur = clavier.nextLine();
            boolean identifiantExiste = existe(identifiantFournisseur, tableauIdentifiantFournisseur2);
            if (identifiantFournisseur.isEmpty()) {
                afficherMessage(MESSAGE_SORTIR_DU_MENU_VALIDE + "MENU_MODIFIER_ATTRIBUT_FOURNISSEUR");
                menuEstFermé = true;
            } else if (identifiantExiste) {
                modifierAttibutFournisseur(identifiantFournisseur);
                menuEstFermé = true;
            } else {
                afficherMessage(MESSAGE_NUMERO_INEXISTANT);
            }
        }
        while (!menuEstFermé);
    }

    //*****************************************************
    //Permet de modifier l'attribut du fournisseur. Elle est utilisée dans la procédure menuModifierAttributFournisseur().
    //*****************************************************
    private static void modifierAttibutFournisseur(String identifiantFournisseur) {
        boolean menuEstFermé;
        do {
            menuEstFermé = false;
            var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
            //le tableauIdentifiantFournisseur2 est en String d'où la fonction transformerStringToIntArray().
            int[] tableauIdentifiantFournisseur = transformerTableauStringToInt(tableauIdentifiantFournisseur2, compteurFournisseur);
            int indexFournisseurTrouve = localiser(identifiantFournisseur, tableauIdentifiantFournisseur, compteur);
            String nomFournisseur = entreeNomFournisseur();
            String numeroTelephone = entreeNumeroTelephoneFournisseur();
            boolean saisiNestPasVide = !saisiEstVide(nomFournisseur) && !saisiEstVide(numeroTelephone);
            if (saisiNestPasVide && numeroTelephoneEstBienEcrit(numeroTelephone)) {
                //Pour modifier les attributs dans le tableau du Fournisseur. Il est a noté qu'il existe deux tableaux
                //différents pour stocker les attributs du fournisseur. Ceci permet d'afficher la liste des fournisseurs plus
                //facilement indépendamment du tableau de l'origine du vêtement.
                tableauFournisseur[indexFournisseurTrouve] = nomFournisseur.toUpperCase();
                tableauTelephoneFournisseur2[indexFournisseurTrouve] = numeroTelephone;
                //Pour modifier les attributs dans le tableau de l'origine.
                for (int i = 0; i < compteur; i++) {
                    if (identifiantFournisseur.equalsIgnoreCase(String.valueOf(InventaireFinal.tableauIdentifiantFournisseur[i]))) {
                        tableauOrigine[i] = nomFournisseur.toUpperCase();
                        tableauTelephoneFournisseur[i] = numeroTelephone;
                    }
                }
                afficherMessage(COULEUR_BLUE + "Yeahhh le fournisseur avec l'identifiant " + identifiantFournisseur +
                        " a été modifié avec succès." + COULEUR_RESET);
                menuEstFermé = true;
            }
        }
        while (!menuEstFermé);
    }

    //*****************************************************
    //Permet de transformer un tableau en String en int. Par erreur le tableauIdentifiantFournisseur,
    //le tableauIdentifiantFournisseur2, le tableauTelephoneFournisseur et le tableauTelephoneFournisseur2 ont été
    //déclarés en String[] au lieu de int[].
    //*****************************************************
    public static int[] transformerTableauStringToInt(String[] tableau, int longeurTableau) {
        int[] intTableau = new int[longeurTableau];
        for (int i = 0; i < longeurTableau; i++) {
            intTableau[i] = Integer.parseInt(tableau[i]);
        }
        return intTableau;
    }

    //*****************************************************
    //Permet de lister les fournisseurs actuel.
    //*****************************************************
    public static void listerFournisseur() {
        System.out.println(COULEUR_CYAN + "\n\t\tLA LISTE DES FOURNISSEURS" + COULEUR_RESET);
        afficherEnteteFournisseur();
        int itemcount = 0;
        for (int i = 0; i < compteurFournisseur; i++) {
            String ch;
            ch = fixeS(tableauIdentifiantFournisseur2[i], 15, null) + "| "
                    + fixeS(tableauFournisseur[i].toUpperCase(), 25, null) + "| "
                    + fixeS(tableauTelephoneFournisseur2[i], 30, null) + "| ";
            System.out.println(ch);
            itemcount = itemcount + 1;
        }
        System.out.println(COULEUR_CYAN_BRIGHT + "Actuellement vous avez " + itemcount + " FOURNISSEURS" + COULEUR_RESET);

    }

    //*****************************************************
    //Procédure permettant d'afficher l'entête de la liste du fournisseur
    //*****************************************************
    public static void afficherEnteteFournisseur() {
        String ch;
        ch = fixeS("", 110, null);
        System.out.println(ch);
        ch = fixeS("IDENTIFIANT", 15, null) + "| "
                + fixeS("    FOURNISSEUR", 25, null) + "| "
                + fixeS("******** NUMERO TELEPHONE ********", 30, null) + "| ";
        System.out.println(ch);
        ch = fixeS("", 110, null);
        System.out.println(ch);
    }

    //*****************************************************
    //Fonction qui retourne une chaine de longueur fixe;
    //La chaine initiale est tronquée si trop longue, ou
    //complétée avec le padding si trop courte
    //*****************************************************
    public static String fixeS(String s, int longueur, String pad) {
        String chaineFixe = "";
        String espace = " ";
        boolean fin = false;
        int i = 0;

        if (pad == null) {
            pad = espace;
        }
        while (i < longueur) {
            if (i == s.length()) {
                fin = true;
            }
            if (!fin) {
                chaineFixe = chaineFixe + s.charAt(i);
            } else {
                chaineFixe = chaineFixe + pad;
            }
            i++;
        }
        return chaineFixe;
    }

    //*****************************************************
    //Permet de lister les vêtement en fonction du fournisseur. On prend comme référence l'identifiant du fournisseur.
    //*****************************************************
    public static void listerItemEnFonctionDuFournisseur(String[] tableau) {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        boolean fermerLeMenu;
        do {
            fermerLeMenu = false;
            listerFournisseur();
            System.out.println("\nQuel fournisseur voulez-vous lister ses vêtements ?\nEntrez son identifiant :" +
                    "\n" + MESSAGE_SORTIR_DU_MENU);
            String choixIdentifiant = clavier.nextLine();
            boolean identifiantExiste = existe(choixIdentifiant, tableau);
            boolean identifiantExiste2 = existe(choixIdentifiant, tableauIdentifiantFournisseur2);
            String fournisseur = " ";
            int itemCount = 0;
            if (choixIdentifiant.isEmpty()) {
                fermerLeMenu = true;
            } else if (identifiantExiste) {
                for (int i = 0; i < compteur; i++) {
                    if (choixIdentifiant.equalsIgnoreCase(tableau[i])) {
                        afficherInventaire(i);
                        itemCount = itemCount + 1;
                        fournisseur = tableauOrigine[i];
                    }
                }
                System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement vous avez " + (itemCount) + " item(s) produit(ent) par "
                        + fournisseur.toUpperCase() + " dans votre inventaire\n" + COULEUR_RESET);
            } else if (identifiantExiste2 && !identifiantExiste) {
                System.out.println(COULEUR_CYAN_BRIGHT + "\nActuellement ce fournisseur ne fournit pas de vêtement. " + COULEUR_RESET);
            } else {
                afficherMessage(MESSAGE_NUMERO_INEXISTANT);
            }
        }
        while (!fermerLeMenu);

    }

    //*****************************************************
    //Fonction permettant de demander le numéro du vêtement à l'utilisateur.
    //*****************************************************
    public static String entreeNumeroVetement() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("\nEntrez le numéro du vêtement ou " + MESSAGE_SORTIR_DU_MENU);
        String numéroVêtement = clavier.nextLine(); // Pour stocker le numéro
        if (!estReel(numéroVêtement, MESSAGE_TRAITEMENT_CODE)) {
            return entreeNumeroVetement();
        }
        return numéroVêtement;
    }

    //*****************************************************
    //Fonction permettant de checker si le numéro du vêtement existe.
    //*****************************************************
    private static boolean numéroVêtementExiste(String numéroVêtement) {
        int indexNumeroItem = localiser(numéroVêtement, tableauNumeroVetement, compteur);
        if (indexNumeroItem >= 0) { // Si c'est le cas, le numéro du vêtement existe déjà.
            return true;
        }
        return false;
    }

    //*****************************************************
    //Fonction permettant de demander le descriptif du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeDescriptif() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez le descriptif du vêtement ou " + MESSAGE_SORTIR_DU_MENU);
        String descriftif = clavier.nextLine();
        if (!descriftif.isEmpty() && !descriftifEstBienEcrit(descriftif)) {
            return entreeDescriptif();
        }
        return descriftif;
    }

    //*****************************************************
    //Fonction permettant de demander le sexe du vêtement à l'utilisateur.
    //*****************************************************
    public static String entreeSexe() {
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez le sexe Hommes (1), Femmes (2), Unisexes (3):  \nou " + MESSAGE_SORTIR_DU_MENU);
        String choixSexe = clavier.nextLine();
        boolean isChoixSexe = (choixSexe.isEmpty() ||
                (Integer.parseInt(choixSexe) >= 1 && Integer.parseInt(choixSexe) <= 3));
        if (!isChoixSexe) {
            System.out.println("\n" + MESSAGE_SAISI_INEXISTANT);
            return entreeSexe();
        }
        return choixSexe;
    }

    //*****************************************************
    //Fonction permettant de demander la quantité du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeQuantite() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez la quantité du vêtement ou " + MESSAGE_SORTIR_DU_MENU);
        String newQuantite = clavier.nextLine();
        if (!estReel(newQuantite, MESSAGE_TRAITEMENT_QUANTITE)) {
            return entreeQuantite();
        }
        return newQuantite;
    }

    //*****************************************************
    //Fonction permettant de demander la quantité minimum du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeQuantiteMin() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez la quantité minimale OU " + MESSAGE_SORTIR_DU_MENU);
        String newQuantiteMin = clavier.nextLine();
        if (!estReel(newQuantiteMin, MESSAGE_TRAITEMENT_QUANTITE)) {
            return entreeQuantiteMin();
        }
        return newQuantiteMin;
    }

    //*****************************************************
    //Fonction permettant de demander les informations sur l'origine du vêtement et à la fin retourne un tableau
    //tableauAttributOrigine. Ce tableau contient 3 valeurs notamment l'identifiant du fabriquant, le nom du fabriquant
    // et son numéro de téléphone respectivement.
    //*****************************************************
    public static String[] entreeOrigine() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        String numeroFabriquant; // // pour stocker l'identifiant du fabriquant
        String nomFabriquant; // pour stocker le nom du fabriquant
        String identifiantOrigine; // pour stocker le numéro qui identifie le fabriquant ou l'origine
        System.out.print("C'est fabriqué par qui ? Nous (1), Autres (entrez autre chose): ");
        String input = clavier.nextLine();
        if (input.equalsIgnoreCase(String.valueOf(1))) {
            nomFabriquant = "Nous meme";
            numeroFabriquant = NUMERO_TELEPHONE_ENTREPRISE;
            identifiantOrigine = "1";
        } else {
            listerFournisseur();// Pour l'aider à se referer.
            String[] tableauAttributFournisseur = entreeAttributFournisseur();
            identifiantOrigine = tableauAttributFournisseur[0];
            nomFabriquant = tableauAttributFournisseur[1];
            numeroFabriquant = tableauAttributFournisseur[2];
        }
        String[] tableauAttributOrigine = new String[]{identifiantOrigine, nomFabriquant, numeroFabriquant};
        return tableauAttributOrigine;
    }

    //*****************************************************
    //Fonction permettant de traiter les attributs du fournisseur et les retournés bien après dans un tableau
    //tableauAttributFournisseur. Ce tableau contient 3 valeurs, l'identifiant du fournisseur, le nom du fournisseur
    // et son numéro de téléphone respectivement.
    //*****************************************************
    public static String[] entreeAttributFournisseur() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        boolean entreeEstValide;
        // contient les attributs du fournisseurs. C-a-d l'identifiant, le nom et le numéro de téléphone.
        String[] tableauAttributFournisseur;
        String nomFournisseur = ""; // Pour stocker son nom
        String numéroTelephoneFournisseur = ""; //Pour stocker son numéro de téléphone
        String identifiantFournisseur = entreeIdentifiantFournisseur(); // pour stocker l'identifiant du fournisseur
        //pour checker si le bon chiffre du fabriquant a été saisi
        int[] tableauIdentifiantFournisseur2 = transformerTableauStringToInt(tableauIdentifiantFournisseur, compteur);
        int indexFournisseurTrouve = localiser(identifiantFournisseur, tableauIdentifiantFournisseur2, compteur);
        if (indexFournisseurTrouve < 0) {// Si le fournisseur n'existe pas.
            nomFournisseur = entreeNomFournisseur();
            numéroTelephoneFournisseur = entreeNumeroTelephoneFournisseur();
        } else {
            // Si le fournisseur existe on demande à l'utilisateur par mesure de sécurité si c'est vraiment ce fournisseur
            //qu'il veut choisir, sinon on lui demande d'entrée les informations du nouveau fournisseur en question.
            do {
                entreeEstValide = false;
                afficherInformationItemChoisi(indexFournisseurTrouve, tableauOrigine, "au fournisseur ");
                System.out.print("Est-ce lui le fournisseur (1) ou vous vouliez plutôt entrez un nouveau (2) : ");
                switch (clavier.nextLine()) {
                    //Si c'est exactement le fournisseur qu'il voulait, on capture les attributs existant notamment déjà
                    //dans les tableaux.
                    case "1":
                        System.out.println(COULEUR_BLUE + tableauOrigine[indexFournisseurTrouve].toUpperCase() + COULEUR_RESET
                                + " sera le fournisseur de ce vêtement.");
                        nomFournisseur = tableauOrigine[indexFournisseurTrouve];
                        numéroTelephoneFournisseur = tableauTelephoneFournisseur[indexFournisseurTrouve];
                        entreeEstValide = true;
                        break;
                    // s'il voulait plutôt entrez un autre fournisseur. Cet fonction est appelé.(RECURSIVE CALL)
                    case "2":
                        tableauAttributFournisseur = entreeAttributFournisseur();
                        identifiantFournisseur = tableauAttributFournisseur[0];
                        nomFournisseur = tableauAttributFournisseur[1];
                        numéroTelephoneFournisseur = tableauAttributFournisseur[2];
                        entreeEstValide = true;
                        break;
                    default:
                        afficherMessage(MESSAGE_SAISI_INEXISTANT);
                        break;
                }
            }
            while (!entreeEstValide);
        }
        tableauAttributFournisseur = new String[]{identifiantFournisseur, nomFournisseur, numéroTelephoneFournisseur};
        return tableauAttributFournisseur;
    }

    //*****************************************************
    //Fonction permettant de demander le numéro de téléphone du fournisseur du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeNumeroTelephoneFournisseur() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez son numéro de téléphone: ");
        String numéroTelephoneFournisseur = clavier.nextLine();
        if (saisiEstVide(numéroTelephoneFournisseur) && !numeroTelephoneEstBienEcrit(numéroTelephoneFournisseur)) {
            return entreeNumeroTelephoneFournisseur();
        }
        return numéroTelephoneFournisseur;
    }

    //*****************************************************
    //Fonction permettant de demander le nom du fournisseur du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeNomFournisseur() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez le nom du fabriquant: ");
        String nomFournisseur = clavier.nextLine();
        if (saisiEstVide(nomFournisseur)) {
            return entreeNomFournisseur();
        }
        return nomFournisseur;
    }

    //*****************************************************
    //Fonction permettant de demander le code d'identifiant du fournisseur du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeIdentifiantFournisseur() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez le code d'identifiant du fournisseur: ");
        String identifiantFournisseur = clavier.nextLine();// pour stocker l'identifiant du fournisseur
        if (saisiEstVide(identifiantFournisseur) && !estReel(identifiantFournisseur, MESSAGE_TRAITEMENT_CODE_IDENTIFIANT)) {
            return entreeIdentifiantFournisseur();
        }
        return identifiantFournisseur;
    }

    //*****************************************************
    //Fonction permettant de demander le prix de vente du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreePrixDeVente() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez le prix de vente ou " + MESSAGE_SORTIR_DU_MENU);
        String newPrixDeVente = clavier.nextLine();
        if (!PrixEstBienSaisi(newPrixDeVente)) {
            return entreePrixDeVente();
        }
        return newPrixDeVente;
    }

    //*****************************************************
    //Fonction permettant de demander le prix de revient du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreePrixDeRevient() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Entrez le prix de revient ou " + MESSAGE_SORTIR_DU_MENU);
        String newPrixDeRevient = clavier.nextLine();
        if (!PrixEstBienSaisi(newPrixDeRevient)) {
            return entreePrixDeRevient();
        }
        return newPrixDeRevient;
    }

    //*****************************************************
    //Fonction permettant de demander le statut du vêtement à l'utilisateur.
    //*****************************************************
    private static String entreeStatut() {
        var clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        System.out.print("Est-il périmé (0) ou actif (1) ou " + MESSAGE_SORTIR_DU_MENU);
        String choixStatut = clavier.nextLine();
        boolean isChoixStatut = (choixStatut.isEmpty() ||
                (choixStatut.equalsIgnoreCase("0") || choixStatut.equalsIgnoreCase("1")));
        if (!isChoixStatut) {
            System.out.println("\n" + MESSAGE_SAISI_INEXISTANT);
            return entreeStatut();
        }
        return choixStatut;
    }

    //*****************************************************
    //Menu permettant de prendre tous les attributs d'un vêtement. Notamment le numéro du vêtement,
    //le descriptif, le sexe, la quantité, la quantité minimale, l'origine, le prix de revient, le prix de vente,
    //les informations sur le fournisseur et l'état du vêtement.
    //*****************************************************
    public static void menuAjouter() {
        //À noter quand la saisi vide est détecter ce menu se ferme. Donc à chaque entrée de l'utilisateur, ceci est
        //checker avec de procéder à la demande du prochain entrée.
        if (compteur == longueurTableau) {
            System.out.println(COULEUR_RED + "Désolé l'inventaire est plein" + COULEUR_RESET);
        } else {
            afficherMessage(MESSAGE_MENU_AJOUTER_VETEMENT);
            String numeroVetement = entreeNumeroVetement();
            if (numéroVêtementExiste(numeroVetement)) {
                System.out.println(MESSAGE_NUMERO_EXISTANT);
                menuAjouter();
                return;
            }
            if (numeroVetement.isEmpty()) {
                afficherMessage(MESSAGE_SAUVEGARDE_ECHEC);
                return;
            }
            String descriftif = getString(entreeDescriptif());
            String sexe = entreeSexe();
            if (sexe.isEmpty()) {
                menuAjouter();
                return;
            }
            String newQuantite = entreeQuantite();
            if (newQuantite.isEmpty()) {
                menuAjouter();
                return;
            }
            String newQuantiteMin = entreeQuantiteMin();
            if (newQuantiteMin.isEmpty()) {
                menuAjouter();
                return;
            }
            String[] origine = entreeOrigine();
            String identifiantOrigine = origine[0];
            String fabriquant = origine[1];
            String numeroFabriquant = origine[2];
            String newPrixDeRevient = entreePrixDeRevient();
            if (newPrixDeRevient.isEmpty()) {
                menuAjouter();
                return;
            }
            String newPrixDeVente = entreePrixDeVente();
            if (newPrixDeVente.isEmpty()) {
                menuAjouter();
                return;
            }
            String statutItem = entreeStatut();
            if (statutItem.isEmpty()) {
                afficherMessage(MESSAGE_SAUVEGARDE_ECHEC);
                menuAjouter();
                return;
            }
            sauvegarderItem(numeroFabriquant, fabriquant, identifiantOrigine, numeroVetement,
                    descriftif, sexe, newQuantite, newQuantiteMin, newPrixDeRevient,
                    newPrixDeVente, statutItem);
        }
    }

    private static String getString(String s) {
        String descriftif = s;
        if (descriftif.isEmpty()) {
            menuAjouter();
        }
        return descriftif;
    }

    //*****************************************************
    //Permet de sauvegarder les attributs du vêtement dans les tableaux correspondant. Notamment le numéro du vêtement,
    //le descriptif, le sexe, la quantité, la quantité minimale, l'origine, le prix de revient, le prix de vente,
    //les informations sur le fournisseur et l'état du vêtement.
    //*****************************************************
    private static void sauvegarderItem(String numeroFabriquant, String fabriquant, String identifiantOrigine, String
            numeroVet, String descriftif, String gender, String newQuantite, String newQuantiteMin,
                                        String newPrixDeRevient, String newPrixDeVente, String statutItem) {
        tableauNumeroVetement[compteur] = Integer.parseInt(numeroVet);
        tableauDescription[compteur] = descriftif;
        sauvegarderSexe(gender);
        tableauQuantite[compteur] = Integer.parseInt(newQuantite);
        tableauQuantiteMinimum[compteur] = Integer.parseInt(newQuantiteMin);
        sauvegarderOrigineVêtement(numeroFabriquant, fabriquant, identifiantOrigine, compteur);
        tableauPrixDeRevient[compteur] = Float.parseFloat(newPrixDeRevient.replace(",", "."));
        tableauPrixDeVente[compteur] = Float.parseFloat(newPrixDeVente.replace(",", "."));
        sauvegarderFournisseur(numeroFabriquant, fabriquant, identifiantOrigine, compteurFournisseur);
        sauvegarderEtatItem(statutItem, compteur);
        compteur++;
        System.out.println("\n" + MESSAGE_SAUVEGARDE_REUSSI + "\n");
    }

    //*****************************************************
    //Permet de sauvegarder les l'état du vêtement dans le tableau correspondant. Il peut être soit périme ou actif.
    //*****************************************************
    private static void sauvegarderEtatItem(String statutItem, int indexOuCompteur) {
        if (statutItem.equalsIgnoreCase(String.valueOf(0))) {
            tableauStatutEnBoolean[indexOuCompteur] = false;
            tableauStatut[indexOuCompteur] = "périmé";
        } else if (statutItem.equalsIgnoreCase(String.valueOf(1))) {
            tableauStatutEnBoolean[indexOuCompteur] = true;
            tableauStatut[indexOuCompteur] = "actif";
        }
    }

    //*****************************************************
    //Permet de sauvegarder les attributs du fournisseur. Notamment l'identifiant du fournisseur, son nom
    //et le numéro de téléphone.
    //*****************************************************
    private static void sauvegarderFournisseur(String numeroFabriquant, String fabriquant, String
            identifiantOrigine, int indexOuCompteur) {
        boolean fournisseurExiste = existe(identifiantOrigine, tableauIdentifiantFournisseur2);
        if (!fournisseurExiste && !identifiantOrigine.equalsIgnoreCase(String.valueOf(1))) {
            tableauFournisseur[indexOuCompteur] = fabriquant.toUpperCase();
            tableauIdentifiantFournisseur2[indexOuCompteur] = identifiantOrigine;
            tableauTelephoneFournisseur2[indexOuCompteur] = numeroFabriquant;
            compteurFournisseur++;
        }
    }

    //*****************************************************
    //Permet de sauvegarder les attributs de l'origine d'un vêtement. Notamment l'identifiant du fabriquant, son nom
    //et le numéro de téléphone.
    //*****************************************************
    private static void sauvegarderOrigineVêtement(String numeroFabriquant, String nomFabriquant, String identifiantOrigine,
                                                   int indexOUcompteur) {
        boolean identifiantExiste = existe(identifiantOrigine, tableauIdentifiantFournisseur);
        if (identifiantExiste) {
            for (int i = 0; i < indexOUcompteur; i++) {
                if (identifiantOrigine.equalsIgnoreCase(tableauIdentifiantFournisseur[i])) {
                    tableauOrigine[indexOUcompteur] = tableauOrigine[i];
                    tableauIdentifiantFournisseur[indexOUcompteur] = tableauIdentifiantFournisseur[i];
                    tableauTelephoneFournisseur[indexOUcompteur] = tableauTelephoneFournisseur[i];
                }
            }
        } else {
            tableauOrigine[indexOUcompteur] = nomFabriquant.toUpperCase();
            tableauIdentifiantFournisseur[indexOUcompteur] = identifiantOrigine;
            tableauTelephoneFournisseur[indexOUcompteur] = numeroFabriquant;
        }
    }

    //*****************************************************
    //Permet de sauvegarder le sexe saisi par l'utilisateur dans le tableau correspondant.
    //*****************************************************
    private static void sauvegarderSexe(String gender) {
        switch (gender) {
            case "1":
                tableauSexe[compteur] = "Homme";
                break;
            case "2":
                tableauSexe[compteur] = "Femme";
                break;
            case "3":
                tableauSexe[compteur] = "Unisexe";
                break;
        }
    }

    //*****************************************************
    //Permet de checker si un élément existe dans un tableau.
    //*****************************************************
    public static boolean existe(String chaine, String[] tableau) {
        for (int idx = 0; idx < compteur; idx++) {
            if (chaine.equalsIgnoreCase(String.valueOf((tableau[idx])))) {
                return true;
            }
        }
        return false;
    }

    //*****************************************************
    //Permet d'afficher le choix du menu principale de l'inventaire et de récupérer le choix saisi par l'utilisateur.
    //*****************************************************
    public static String choixAfficherMenu() {
        // Le menu permettant a l'utilisateur de naviguer dans le programme
        System.out.println("\n\n" + COULEUR_BLACK_BACKGROUND + COULEUR_GREEN + "=====================================================" + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "               Gestionnaire d'Inventaire             " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "                     de vêtements                    " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + COULEUR_GREEN + "=====================================================" + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "* Pour ajouter un vêtement (1)                       " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "* Pour modifier les attributs d'un vêtement (2)      " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "* Pour modifier les attributs du fournisseur (3)     " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "* Pour supprimer un vêtement (4)                     " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "* Pour liste l'inventaire tapez (5)                  " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + "* Pour obtenir la valeur totale de l'inventaire (6)  " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + COULEUR_RED + "* Pour fermer l'utilitaire (0)                       " + COULEUR_RESET);
        System.out.println(COULEUR_BLACK_BACKGROUND + COULEUR_GREEN + "=====================================================" + COULEUR_RESET + "\n");
        Scanner clavier = new Scanner(System.in); // Scanner pour lire la saisi de l'utilisateur
        return clavier.nextLine();
    }

    //*****************************************************
    //Permet de signaler si une saisi est vide.
    //*****************************************************
    public static boolean saisiEstVide(String choix) {
        if (choix.isEmpty()) {
            System.out.println(COULEUR_RED + "Saisi vide détectée, recommencez. Merci!" + COULEUR_RESET);
            return true;
        }
        return false;
    }

    //*****************************************************
    //Permet de formater le numéro de Téléphone du fournisseur. Le numéro de téléphone du fournisseur ne peut comporter
    //que des chiffres, le trait-d’union et l’espace.
    //*****************************************************
    public static boolean numeroTelephoneEstBienEcrit(String numeroTelephone) {

        boolean estvalide = true;
        String trait_union = "-";
        String espace = " ";
        char[] po = numeroTelephone.toCharArray();
        for (int i = 0; i < numeroTelephone.length(); i++) {
            if (!trait_union.equalsIgnoreCase(String.valueOf(po[i])) && !Character.isDigit(po[i])
                    && !espace.equalsIgnoreCase(String.valueOf(po[i]))) {
                estvalide = false;
            }
        }
        if (!estvalide) {
            System.out.println(COULEUR_RED + "le numéro de téléphone du fournisseur ne peut comporter " +
                    "que des chiffres, le trait-d’union et l’espace; Merci! " + COULEUR_RESET);
        }
        return estvalide;
    }

    //*****************************************************
    //Permet de formater le descriptif du vêtement. Le descriptif du vêtement commence par une lettre.
    //*****************************************************
    public static boolean descriftifEstBienEcrit(String mot) {
        boolean estBienEcrit = true;
        String nouveauMot = mot.trim() + " ";
        char[] nouveauMot2 = nouveauMot.toCharArray();
        if (!Character.isLetter(nouveauMot2[0])) {
            System.out.println(COULEUR_RED + "Le descriptif du vêtement commence par une lettre s'il vous plait"
                    + COULEUR_RESET);
            estBienEcrit = false;
        }
        return estBienEcrit;
    }

    //*****************************************************
    //Permet de formater le prix. Le prix ne contient que des chiffres, la virgule ou un point et ne peut être supérieur
    //à $1000.00 ou être négatif.
    //*****************************************************
    public static boolean PrixEstBienSaisi(String prix) {

        boolean estPositif = true;
        String point = ".";
        String virgule = ",";
        String nouveauPrix = "1001.00"; // Par défaut le prix est supérieur à 1000.
        int idx = 0;

        // Le prix ne peut commencer par un point ni une virgule. Ici on considère juste les points. Quand une virgule est
        //détectée, elle est remplacée par un point. Aussi au cas ou plusieurs point sont compris dans le prix idx est incrémenté.
        if (!prix.startsWith(point) && !prix.startsWith(virgule) && formatPrixEstBon(prix.trim())) {
            nouveauPrix = prix.replace(virgule, point);
            char[] po = nouveauPrix.toCharArray();
            for (int i = 0; i < nouveauPrix.length(); i++) {
                if (point.equalsIgnoreCase(String.valueOf(po[i]))) {
                    idx++;
                }
            }
        }

        //Si le prix contient moins de 2 points, le prix est valide et on traite maintenant l'intervalle.
        if (idx < 2) {
            float prixEnFloat = Float.parseFloat(nouveauPrix);
            if (((prixEnFloat < 0 || prixEnFloat > 1000))) {
                estPositif = false;
                System.out.println(COULEUR_RED + "Les prix sont forcément positifs, ne contient que des chiffres" +
                        " et ne peuvent être supérieurs à $1000. Merci!" + COULEUR_RESET);
            }
        } else {
            estPositif = false;
            System.out.println(COULEUR_RED + "Vous avez saisi soit beaucoup de virgules ou plusieurs points. " +
                    "Recommencez s'il vous plaît." + COULEUR_RESET);
        }
        return estPositif;

    }

    //*****************************************************
    //Permet de formater le prix. Le prix ne contient que des chiffres, la virgule ou un point. Elle sera utilisé dans
    //fonction prixEstBienSaisi().
    //*****************************************************
    public static boolean formatPrixEstBon(String prix) {
        boolean estBon = true;
        char[] tableauPrix = prix.toCharArray();
        String virgule = ",";
        String point = ".";
        for (int i = 0; i < prix.length(); i++) {
            boolean isPrixFormat = point.equalsIgnoreCase(String.valueOf(tableauPrix[i])) &&
                    virgule.equalsIgnoreCase(String.valueOf(tableauPrix[i]));
            if (!isPrixFormat && !Character.isDigit(tableauPrix[i])) {
                estBon = false;
            }
        }
        return estBon;
    }

    //*****************************************************
    //Cet fonction permet de localiser un attribut et récupérer son index dans le tableau correspondant.
    //*****************************************************
    public static int localiser(String chaine, int[] tableau, int compteur) {
        for (int idx = 0; idx < compteur; idx++) {
            boolean isChaineExiste = chaine.equalsIgnoreCase(String.valueOf(tableau[idx]));
            if (isChaineExiste) {
                return idx;
            }
        }
        return -1;
    }

    //*****************************************************
    //Permet d'afficher des messages.
    //*****************************************************
    public static void afficherMessage(String messageAfficher) {
        System.out.println(messageAfficher);
    }

    //*****************************************************
    //Fonction pour fermer le programme quand (0) est saisi.
    //*****************************************************
    private static boolean programmeEstFerme(String choixMenu) {
        boolean estFermé = choixMenu.equalsIgnoreCase(String.valueOf(0));
        if (!estFermé) {
            afficherMessage(MESSAGE_SAISI_INEXISTANT);
            return false;
        }
        afficherMessage(MESSAGE_PROGRAMME_FERME);
        return true;
    }
    // </editor-folder> MES MÉTHODES

} //fin class inventaire
