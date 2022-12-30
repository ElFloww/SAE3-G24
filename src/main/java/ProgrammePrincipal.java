import JDBC.ConnexionBase;
import dao.DAO;
import dao.DAOFactory;
import dao.HeureRestanteDAO;
import dao.UtilisateurDAO;
import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProgrammePrincipal {

    public static void main(String[] args) {

        //Déclarations des objets DAO avec la DAOFactory
        DAO<Type_Enseignement>  enseignementdao =       DAOFactory.getType_EnseignementDAO();
        DAO<Utilisateur>        userdao =               DAOFactory.getUtilisateurDAO();
        DAO<Salle>              salledao =              DAOFactory.getSalleDAO();
        DAO<Ressource>          ressourcedao =          DAOFactory.getRessourceDAO();
        DAO<Regroupement>       regroupementdao =       DAOFactory.getRegroupementDAO();
        DAO<Jour>               jourdao =               DAOFactory.getJourDAO();
        DAO<HeureTotalPlacer>   heureaplacerdao =       DAOFactory.getHeureTotalPlacerDAO();
        DAO<HeureRestante>      heurerestantedao =      DAOFactory.getHeureRestanteDAO();
        DAO<Heure>              heuredao =              DAOFactory.getHeureDAO();
        DAO<Disponibilite>      disponibilitedao =      DAOFactory.getDisponibiliteDAO();
        DAO<DisponibiliteProf>  disponibiliteprofdao =  DAOFactory.getDisponibiliteProfDAO();
        DAO<DisponibiliteSalle> disponibiliteSalledao = DAOFactory.getDisponibiliteSalleDAO();
        DAO<Cours>              coursdao =              DAOFactory.getCoursDAO();

        Utilisateur monUtilisateur = userdao.find(2);

/*

        List<Utilisateur> ListUtilisateur = userdao.findAll();
        for (Utilisateur user : ListUtilisateur)
        {
            if(user.getStatut().equals("Enseignant") || user.getStatut().equals("Enseignante"))
            {
                disponibilites_prof(user);
            }
        }

        List<Salle> ListSalle = salledao.findAll();
        for (Salle salle : ListSalle)
        {
            disponibilites_salle(salle);
        }


        List<Regroupement> ListRegroupement = regroupementdao.findAll();
        for (Regroupement unRegroupement : ListRegroupement)
        {
            disponibilites_groupe(unRegroupement,2);
        }
        */
        interfaceUtilisateur();

        ConnexionBase.DeconnexionBase();
    }

    public static void affichageEDT(){
        Scanner input = new Scanner(System.in);
        DAO<Regroupement> regroupementdao = DAOFactory.getRegroupementDAO();
        boolean termine = false;
        while (!termine) {
            System.out.println("Tapper l'identifiant du regroupement correspondant à votre recherche | (0) : Affichage de tous les regroupement dans la base de données | (-1) Quitter cette page");
            int choix = input.nextInt();
            switch (choix) {
                case 0:
                    List<Regroupement> ListRegroupement = regroupementdao.findAll();
                    for (Regroupement unRegroupement : ListRegroupement) {
                        System.out.println(unRegroupement);
                    }
                    break;
                case -1:
                    termine = true;
                    break;
                default:
                    disponibilites_groupe(regroupementdao.find(choix),choixSemaine());
                    break;
            }
        }
    }
    public static int choixSemaine()
    {
        int resultat = 1;
        Scanner input = new Scanner(System.in);
        boolean termine = false;
        while(!termine)
        {
            System.out.print("Saisissez le numéro de la semaine : ");
            int choix = input.nextInt();
            if(choix > 0 && choix <=53)
            {
                resultat = choix;
                termine = true;
            }
        }
        return resultat;
    }

    public static void affichageDisponibiliteSalle(){
        Scanner input = new Scanner(System.in);
        DAO<Salle> salledao = DAOFactory.getSalleDAO();
        boolean terminepetiteboucle = false;
        while (!terminepetiteboucle) {
            System.out.println("Tapper l'identifiant de la salle correspondant à votre recherche | (0) : Affichage de toutes les salles dans la base de données | (-1) Quitter cette page");
            int choix = input.nextInt();
            switch (choix) {
                case 0:
                    List<Salle> ListSalle = salledao.findAll();
                    for (Salle uneSalle : ListSalle) {
                        System.out.println(uneSalle);
                    }
                    break;
                case -1:
                    terminepetiteboucle = true;
                    break;
                default:
                    disponibilites_salle(salledao.find(choix));
                    break;
            }
        }
    }
    public static void affichageDisponibiliteProfesseur() {
        Scanner input = new Scanner(System.in);
        DAO<Utilisateur> userdao = DAOFactory.getUtilisateurDAO();
        boolean terminepetiteboucle = false;
        while (!terminepetiteboucle) {
            System.out.println("Tapper l'identifiant utilisateur correspondant à votre recherche | (0) : Affichage de tous les professeurs dans la base de données | (-1) Quitter cette page");
            int choix = input.nextInt();
            switch (choix) {
                case 0:
                    List<Utilisateur> ListUtilisateur = userdao.findAll();
                    for (Utilisateur user : ListUtilisateur) {
                        if(user.getStatut().equals("Enseignant") || user.getStatut().equals("Enseignante"))
                        System.out.println(user);
                    }
                    break;
                case -1:
                    terminepetiteboucle = true;
                    break;
                default:
                    disponibilites_prof(userdao.find(choix));
                    break;
            }
        }
    }
    public static void interfaceUtilisateur()
    {
        Scanner input = new Scanner(System.in);
        boolean termine = false;
        System.out.println("Bienvenue sur le gestionnaire d'emploi du temps du groupe n°24.");
        while(!termine)
        {
            System.out.println("Affichages des disponibilités pour : (1) Professeurs, (2) Salle, (3) Groupes, (4) Quitter");
            int saisie = input.nextInt();
            switch(saisie){
                case 1:
                    System.out.println("Vous aller consulter les disponibilités pour les professeurs");
                    affichageDisponibiliteProfesseur();
                    break;
                case 2 :
                    System.out.println("Vous aller consulter les disponibilités pour les salles");
                    affichageDisponibiliteSalle();
                    break;
                case 3 :
                    System.out.println("Vous aller consulter les emploi du temps");
                    affichageEDT();
                    break;
                case -1 :
                    System.out.println("Vous venez de quitter le logiciel");
                    termine = true;
                    break;
                default:
                    System.out.println("Mauvaise saisie");
                    break;
            }
        }
    }
    public static Time addTime(Time T1, Time T2)
    {
        int hour,minute,second;
        second = T1.getSeconds()+T2.getSeconds();
        minute = T1.getMinutes()+T2.getMinutes();
        hour = T1.getHours()+T2.getHours();

        if(second >=60){ second = second - 60; minute = minute +1;}
        if(minute >=60){ minute = minute - 60; hour = hour -1;}
        if(hour >=24){ hour = hour -24;}
        return new Time(hour,minute,second);
    }
    public static void disponibilites_groupe(Regroupement regoupement,int id_semaine)
    {
        Connection maConnexion = ConnexionBase.getInstance();
        try{
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT description_regroupement,nom,prenom,nom_salle,code_ressource,nom_ressource,T1.heure as heuredebut,T2.heure as heureduree,nom_jour,id_semaine \n" +
                    "FROM jour,cours,regroupement,utilisateur,ressource,salle,heure AS T1, heure AS T2 \n" +
                    "WHERE (T1.heure,id_heure_debut) =ANY(select heure,id_heure_debut from heure,cours where id_heure= id_heure_debut)\n" +
                    "AND (T2.heure,id_heure_duree) =ANY(select heure,id_heure_duree from heure,cours where id_heure= id_heure_duree)\n" +
                    "AND cours.id_regroupement = regroupement.id_regroupement AND cours.id_utilisateur = utilisateur.id_utilisateur \n" +
                    "AND cours.id_salle = salle.id_salle AND cours.id_jour = jour.id_jour AND ressource.id_ressource = cours.id_ressource AND cours.id_regroupement = " + regoupement.getId_regroupement() + " AND id_semaine = " + id_semaine + ";");

            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(regoupement.getDescription_regroupement() + " : ");
            while (result.next()) {
                Time heure_deb = result.getTime(7);
                Time heure_duree = result.getTime(8);
                System.out.println("Semaine n°" + result.getString(10) + "\t" + result.getString(9)+ "\t" + result.getString(1) + " : Enseignant -> " + result.getString(2) + " " + result.getString(3) + "\t Salle : " +result.getString(4) + "\tMatière : " + result.getString(5) + " - " + result.getString(6) + " de " + result.getTime(7) + " à " + addTime(result.getTime(7),result.getTime(8)));
            }
            System.out.println("");
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void disponibilites_salle(Salle salle)
    {
        Connection maConnexion = ConnexionBase.getInstance();
        try{
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("Select nom_jour,id_semaine,T1.heure as heuredebut,T2.heure as heureduree from jour,disponibilite,heure as T1, heure as T2 where (T1.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_debut) and (T2.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_duree) and id_disponibilite =ANY( select id_disponibilite from disponibilite_salle where id_salle = " + salle.getId_salle() + ") and disponibilite.id_jour = jour.id_jour ORDER BY id_semaine,jour.id_jour,T1.Heure;");

            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("Salle n°" + salle.getId_salle() + " : " + salle.getNom_salle() + " n'est pas disponible :");
            while (result.next()) {

                System.out.println(result.getString(1) + ", semaine n°" + result.getInt(2) + " de " + result.getTime(3) + " à " + addTime(result.getTime(3),result.getTime(4)));
            }
            System.out.println("");
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void disponibilites_prof(Utilisateur user)
    {

        Connection maConnexion = ConnexionBase.getInstance();
        try{
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("Select nom_jour,id_semaine,T1.heure as heuredebut,T2.heure as heureduree from jour,disponibilite,heure as T1, heure as T2 where (T1.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_debut) and (T2.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_duree) and id_disponibilite =ANY( select id_disponibilite from disponibilite_prof where id_utilisateur = " + user.getId_utilisateur() + ") and disponibilite.id_jour = jour.id_jour ORDER BY id_semaine,jour.id_jour,T1.Heure;");

            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(user.getStatut() + " n°" + user.getId_utilisateur() + " : " + user.getNom() + " " + user.getPrenom() + ", est disponible :");
            while (result.next()) {

                System.out.println(result.getString(1) + ", semaine n°" + result.getInt(2) + " de " + result.getTime(3) + " à " + addTime(result.getTime(3),result.getTime(4)));
            }
            System.out.println("");
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}

