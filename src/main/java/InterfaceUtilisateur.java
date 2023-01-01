import dao.DAO;
import dao.DAOFactory;
import entities.*;

import java.util.List;
import java.util.Scanner;

import static JDBC.RequeteSQL.*;

public class InterfaceUtilisateur {

    //Cette fonction permet d'afficher les disponibilités des professeurs
    public static void affichageDisponibiliteProfesseur() {
        //On initialise un objet Scanner pour la saisie de l'utilisateur
        Scanner input = new Scanner(System.in);
        //On initialise une variable DAO<Utilisateur>, car nous en auront besoin pour récupérer les informations des utilisateurs dans la base de données.
        DAO<Utilisateur> userdao = DAOFactory.getUtilisateurDAO();
        boolean termine = false;

        //Tant que l'utilisateur ne ferme pas la page
        while (!termine) {
            System.out.println("Tapper : \n\t(x) : L'identifiant utilisateur recherché\n\t(0) : Affichage de tous les professeurs dans la base de données\n\t(-1) : Quitter cette page");
            try
            {
                //Récupération du choix de l'utilisateur
                int choix = input.nextInt();
                switch (choix) {
                    //Si choix = 0 : On affiche tous les utilisateurs de la base de données qui sont des enseignants ou des enseignantes.
                    case 0 -> {
                        //Récupération de tous les utilisateurs de la base de données
                        List<Utilisateur> ListUtilisateur = userdao.findAll();
                        //Pour chaque utilisateur de la base de données.
                        for (Utilisateur user : ListUtilisateur) {
                            //Si l'utilisateur est un enseignant ou une enseignante.
                            if (user.getStatut().equals("Enseignant") || user.getStatut().equals("Enseignante")) {
                                //On l'affiche
                                System.out.println(user);
                            }
                        }
                    }
                    //Si choix = -1 : L'utilisateur quitte la page du logiciel
                    case -1 -> termine = true;
                    //S'il saisit un autre nombre, on affiche les disponibilités du professeur ayant ce numéro d'identifiant, avec la semaine choisi dans choixSemaine()
                    default -> disponibilites_prof(userdao.find(choix),tools.choixSemaine());
                }
                //Si l'utilisateur saisit un caractère autre qu'un nombre
            } catch(java.util.InputMismatchException e)
            {
                System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                input.nextLine();
            }
        }
    }

    //Cette fonction permet d'afficher les disponibilités des salles
    public static void affichageDisponibiliteSalle(){
        //On initialise un objet Scanner pour la saisie de l'utilisateur
        Scanner input = new Scanner(System.in);
        //On initialise une variable DAO<Salle>, car nous en auront besoin pour récupérer les informations des salles dans la base de données.
        DAO<Salle> salledao = DAOFactory.getSalleDAO();
        boolean termine = false;

        //Tant que l'utilisateur ne ferme pas la page
        while (!termine) {
            System.out.println("Tapper : \n\t(x) : L'identifiant de la salle recherché\n\t(0) : Affichage de toutes les salles dans la base de données\n\t(-1) : Quitter cette page");
            try
            {
                //Récupération du choix de l'utilisateur
                int choix = input.nextInt();
                switch (choix)
                {
                    //Si choix = 0 : On affiche tous les salles de la base de données
                    case 0 -> {
                        //Récupération de toutes les salles de la base de données
                        List<Salle> ListSalle = salledao.findAll();
                        //Pour chaque salle de la base de données.
                        for (Salle uneSalle : ListSalle) {
                            //On l'affiche
                            System.out.println(uneSalle);
                        }
                    }
                    //Si choix = -1 : L'utilisateur quitte la page du logiciel
                    case -1 -> termine = true;
                    //S'il saisit un autre nombre, on affiche les indisponibilités de la salle ayant ce numéro d'identifiant, avec la semaine choisi dans choixSemaine()
                    default -> disponibilites_salle(salledao.find(choix),tools.choixSemaine());
                }
                //Si l'utilisateur saisit un caractère autre qu'un nombre
            } catch (java.util.InputMismatchException e)
            {
                System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                input.nextLine();
            }
        }
    }

    //Cette fonction permet d'afficher l'emploi du temps
    public static void affichageEDT(){
        //On initialise un objet Scanner pour la saisie de l'utilisateur
        Scanner input = new Scanner(System.in);
        //On initialise une variable DAO<Regroupement>, car nous en auront besoin pour récupérer les informations des cours dans la base de données.
        DAO<Regroupement> regroupementdao = DAOFactory.getRegroupementDAO();
        boolean termine = false;

        //Tant que l'utilisateur ne ferme pas la page
        while (!termine) {
            System.out.println("Tapper : \n\t(x) : L'identifiant du regroupement recherché\n\t(0) : Affichage de tous les regroupement dans la base de données\n\t(-1) : Quitter cette page");
            try
            {
                //Récupération du choix de l'utilisateur
                int choix = input.nextInt();
                switch (choix) {
                    //Si choix = 0 : On affiche tous les cours de la base de données
                    case 0 -> {
                        //Récupération de tous les regroupements de la base de données
                        List<Regroupement> ListRegroupement = regroupementdao.findAll();
                        //Pour chaque regroupement de la base de données.
                        for (Regroupement unRegroupement : ListRegroupement) {
                            //On l'affiche
                            System.out.println(unRegroupement);
                        }
                    }
                    //Si choix = -1 : L'utilisateur quitte la page du logiciel
                    case -1 -> termine = true;
                    //S'il saisit un autre nombre, on affiche l'emploi du temps en fonction du numéro de regroupement saisit, avec la semaine choisi dans choixSemaine()
                    default -> disponibilites_groupe(regroupementdao.find(choix), tools.choixSemaine());
                }
                //Si l'utilisateur saisit un caractère autre qu'un nombre
            }catch (java.util.InputMismatchException e)
            {
                System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                input.nextLine();
            }

        }
    }

    //Cette fonction permet d'afficher les disponibilités des professeurs
    public static void affichageTypeCours(){
        //On initialise un objet Scanner pour la saisie de l'utilisateur
        Scanner input = new Scanner(System.in);
        //On initialise une variable DAO<Type_Enseignemnt>, car nous en auront besoin pour récupérer les informations des utilisateurs dans la base de données.
        DAO<Type_Enseignement> typeenseignementdao = DAOFactory.getType_EnseignementDAO();
        boolean termine = false;

        //Tant que l'utilisateur ne ferme pas la page
        while (!termine) {
            System.out.println("Tapper l'identifiant de type de cours :\n\t(1) : Amphithéâtre\n\t(2) : Travaux Pratique\n\t(3) : Travaux Dirigés\n\t(-1) Quitter cette page");
            try
            {
                //Récupération du choix de l'utilisateur
                int choix = input.nextInt();
                switch (choix) {
                    //Si choix = 1,2 ou 3 : On affiche tous les cours de la base de données sont de type 1,2 ou 3, avec la semaine choisi dans choixSemaine()
                    case 1, 2, 3 -> typeCours(typeenseignementdao.find(choix), tools.choixSemaine());
                    //Si choix = -1 : L'utilisateur quitte la page du logiciel
                    case -1 -> termine = true;
                    //S'il saisit un autre nombre, on affiche un message d'erreur de saisie
                    default -> System.out.println("Votre saisie est incorrecte !");
                }
                //Si l'utilisateur saisit un caractère autre qu'un nombre
            } catch (java.util.InputMismatchException e)
            {
                System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                input.nextLine();
            }

        }
    }

    //Cette fonction permet d'afficher les cours qu'il reste à placer
    public static void afficheCoursAPlacer() {
        //On initialise un objet Scanner pour la saisie de l'utilisateur
        Scanner input = new Scanner(System.in);
        //On initialise une variable DAO<Ressource>, car nous en auront besoin pour récupérer les informations des utilisateurs dans la base de données.
        DAO<Ressource> ressourcedao = DAOFactory.getRessourceDAO();
        boolean termine = false;

        //Tant que l'utilisateur ne ferme pas la page
        while (!termine) {
            System.out.println("Tapper : \n\t(x) : L'identifiant de la ressource recherché\n\t(0) : Affichage de toutes les ressources dans la base de données\n\t(-1) : Quitter cette page");
            try {
                //Récupération du choix de l'utilisateur
                int choix = input.nextInt();
                switch (choix) {
                    //Si choix = 0 : On affiche toutes les ressources de la base de données
                    case 0 -> {
                        //Récupération de toutes les ressources de la base de données
                        List<Ressource> ListRessource = ressourcedao.findAll();
                        //Pour chaque ressource de la base de données.
                        for (Ressource uneRessource : ListRessource) {
                            //On l'affiche
                            System.out.println(uneRessource);
                        }
                    }
                    //Si choix = -1 : L'utilisateur quitte la page du logiciel
                    case -1 -> termine = true;
                    //S'il saisit un autre nombre, on affiche les heures à placer pour la ressource ayant ce numéro d'identifiant
                    default -> coursAPlacer(ressourcedao.find(choix));
                }
                //Si l'utilisateur saisit un caractère autre qu'un nombre
            } catch (java.util.InputMismatchException e) {
                System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                input.nextLine();
            }

        }
    }
}
