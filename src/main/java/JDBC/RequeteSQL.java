package JDBC;

import entities.*;
import java.sql.*;

public class RequeteSQL {

    //Cette fonction permet d'afficher des cours en fonction du type d'enseignement passé en paramètre et du numéro de semaine
    public static void typeCours(Type_Enseignement typeEnseignement, int id_semaine)
    {
        //On récupère la connexion à la base de donnée
        Connection maConnexion = ConnexionBase.getInstance();
        try{
            //On exécute la requête permettant de récupérer les cours via le type d'enseignement.
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_semaine,nom_jour,description_regroupement,nom,prenom,nom_salle,code_ressource,nom_ressource,T1.heure as heuredebut,T2.heure as heureduree \n" +
                    "FROM cours,regroupement,type_enseignement,utilisateur,ressource,jour,salle,heure AS T1, heure AS T2 \n" +
                    "WHERE (T1.heure,id_heure_debut) =ANY(select heure,id_heure_debut from heure,cours where id_heure= id_heure_debut)\n" +
                    "AND (T2.heure,id_heure_duree) =ANY(select heure,id_heure_duree from heure,cours where id_heure= id_heure_duree)\n" +
                    "AND cours.id_regroupement = regroupement.id_regroupement AND cours.id_utilisateur = utilisateur.id_utilisateur \n" +
                    "AND cours.id_salle = salle.id_salle AND ressource.id_ressource = cours.id_ressource AND jour.id_jour = cours.id_jour\n" +
                    "AND cours.id_type_enseignement = type_enseignement.id_type_enseignement AND type_enseignement.id_type_enseignement = " + typeEnseignement.getId_type_enseignement() + " AND id_semaine = " + id_semaine + " ORDER BY id_semaine,cours.id_jour,heuredebut;");

            System.out.println("Voici tous les cours qui sont en " + typeEnseignement.getLibelle_type_enseignement() + " pour la semaine n°" + id_semaine + " :");
            //Pour chaque résultat de la requête
            while (result.next()) {
                //On affiche les informations du cour
                System.out.println("\t" + result.getString(2) + "\t Le groupe '" + result.getString(3) + "'\t Enseignant : " + result.getString(4) + " " + result.getString(5) + "\t Salle : " + result.getString(6) + "\tCours : " + result.getString(7) + " - " + result.getString(8) + " de " + result.getTime(9) + " à " + TimeTools.addTime(result.getTime(9),result.getTime(10)));
            }
            System.out.println("\n");
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Cette fonction permet d'afficher des cours à placer en fonction de la ressource passé en paramètre
    public static void coursAPlacer(Ressource uneRessource)
    {
        //On récupère la connexion à la base de donnée
        Connection maConnexion = ConnexionBase.getInstance();
        try
        {
            //On exécute la requête permettant de récupérer les cours restant via la ressource.
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT regroupement.description_regroupement,ressource.code_ressource,ressource.nom_ressource,type_enseignement.libelle_type_enseignement,nombre_heure_restante\n" +
                    "FROM heure_restante,ressource,type_enseignement,regroupement\n" +
                    "WHERE ressource.id_ressource = heure_restante.id_ressource \n" +
                    "AND type_enseignement.id_type_enseignement = heure_restante.id_type_enseignement \n" +
                    "AND heure_restante.id_regroupement = regroupement.id_regroupement\n" +
                    "AND nombre_heure_restante != '00:00:00' AND ressource.id_ressource = " + uneRessource.getId_ressource() + " \n" +
                    "ORDER BY code_ressource,description_regroupement;");
            //Si le cours existe dans la base de donnée
            if(uneRessource.getNom_ressource() != null)
            {
                System.out.println("Les cours à placer pour la matière " + uneRessource.getCode_ressource() + " - " + uneRessource.getNom_ressource() + " sont :");
                //Pour chaque résultat de la requête
                while (result.next()) {
                    //On affiche les informations des cours à placer
                    System.out.println("\tGroupe : " + result.getString(1) + "\t Type : " + result.getString(4) + "\t Nombre d'heure à placer : " + result.getString(5));
                }
            }
            else
            {
                System.out.println("Cette ressource n'existe pas !");
            }
            System.out.println("\n");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Cette fonction permet d'afficher l'emploi du temps des groupes en fonction du groupe passé en paramètre et du numéro de semaine.
    public static void disponibilites_groupe(Regroupement regroupement, int id_semaine)
    {
        //On récupère la connexion à la base de donnée
        Connection maConnexion = ConnexionBase.getInstance();
        try{
            //On exécute la requête permettant de récupérer les cours via regroupement
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT description_regroupement,nom,prenom,nom_salle,code_ressource,nom_ressource,T1.heure as heuredebut,T2.heure as heureduree,nom_jour,id_semaine \n" +
                    "FROM jour,cours,regroupement,utilisateur,ressource,salle,heure AS T1, heure AS T2 \n" +
                    "WHERE (T1.heure,id_heure_debut) =ANY(select heure,id_heure_debut from heure,cours where id_heure= id_heure_debut)\n" +
                    "AND (T2.heure,id_heure_duree) =ANY(select heure,id_heure_duree from heure,cours where id_heure= id_heure_duree)\n" +
                    "AND cours.id_regroupement = regroupement.id_regroupement AND cours.id_utilisateur = utilisateur.id_utilisateur \n" +
                    "AND cours.id_salle = salle.id_salle AND cours.id_jour = jour.id_jour AND ressource.id_ressource = cours.id_ressource AND cours.id_regroupement = " + regroupement.getId_regroupement() + " AND id_semaine = " + id_semaine + " ORDER BY cours.id_jour,T1.heure;");
            //Si le cours existe dans la base de donnée
            if(regroupement.getDescription_regroupement() != null)
            {
                //Pour chaque résultat de la requête
                System.out.println(regroupement.getDescription_regroupement() + " - semaine n°" + id_semaine + " : ");
                while (result.next()) {
                    //On affiche les informations des cours à placer
                    System.out.println("\t"+result.getString(9)+ "\tEnseignant -> " + result.getString(2) + " " + result.getString(3) + "\t Salle : " +result.getString(4) + "\tMatière : " + result.getString(5) + " - " + result.getString(6) + " de " + result.getTime(7) + " à " + TimeTools.addTime(result.getTime(7),result.getTime(8)));
                }
                System.out.println("\n");
            }
            else
            {
                System.out.println("L'identifiant de groupe saisit est incorrect !");
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Cette fonction permet d'afficher les indisponibilités des salles en fonction de la salle passé en paramètre et du numéro de semaine.
    public static void disponibilites_salle(Salle salle, int id_semaine)
    {
        //On récupère la connexion à la base de donnée
        Connection maConnexion = ConnexionBase.getInstance();
        try{
            //On exécute la requête permettant de récupérer les indisponibilités de la salle passé en paramètre, pendant la semaine id_semaine passé en paramètre
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("Select nom_jour,id_semaine,T1.heure as heuredebut,T2.heure as heureduree from jour,disponibilite,heure as T1, heure as T2 where (T1.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_debut) and (T2.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_duree) and id_disponibilite =ANY( select id_disponibilite from disponibilite_salle where id_salle = " + salle.getId_salle() + ") and disponibilite.id_jour = jour.id_jour AND id_semaine = " + id_semaine + " ORDER BY id_semaine,jour.id_jour,T1.Heure;");
            //Si la salle existe dans la base de donnée
            if(salle.getNom_salle() != null)
            {
                System.out.println("Semaine n°" + id_semaine + "\tSalle n°" + salle.getId_salle() + " : " + salle.getNom_salle() + " n'est pas disponible :");
                //Pour chaque résultat de la requête
                while (result.next()) {
                    //On affiche les informations des indisponibilités de la salle
                    System.out.println("\t" + result.getString(1) + " de " + result.getTime(3) + " à " + TimeTools.addTime(result.getTime(3),result.getTime(4)));
                }
                System.out.println("\n");
            }
            else
            {
                System.out.println("Cette salle n'existe pas !");
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Cette fonction permet d'afficher les disponibilités des professeurs en fonction de l'utilisateur passé en paramètre et du numéro de semaine.
    public static void disponibilites_prof(Utilisateur user, int id_semaine)
    {
        //On récupère la connexion à la base de donnée
        Connection maConnexion = ConnexionBase.getInstance();
        try{
            //On exécute la requête permettant de récupérer les disponibilités des professeurs passés en paramètre, pendant la semaine id_semaine passé en paramètre.
            ResultSet result = maConnexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("Select nom_jour,id_semaine,T1.heure as heuredebut,T2.heure as heureduree from jour,disponibilite,heure as T1, heure as T2 where (T1.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_debut) and (T2.heure,id_disponibilite) =ANY(select heure,id_disponibilite from  heure,disponibilite where id_heure= id_heure_duree) and id_disponibilite =ANY( select id_disponibilite from disponibilite_prof where id_utilisateur = " + user.getId_utilisateur() + ") and disponibilite.id_jour = jour.id_jour AND id_semaine = " + id_semaine + " ORDER BY id_semaine,jour.id_jour,T1.Heure;");

            //Si l'utilisateur existe dans la base de donnée et qu'il est Enseignant ou Enseignante
            if(user.getStatut() != null && (user.getStatut().equals("Enseignant") || user.getStatut().equals("Enseignante")))
            {
                System.out.println("Utilisateur n°" + user.getId_utilisateur() + " : " + user.getStatut() + " " + user.getNom() + " " + user.getPrenom() + ", est disponible :");
                //Pour chaque résultat de la requête
                while (result.next()) {
                    //On affiche les informations des disponibilités du professeur
                    System.out.println("\t"+result.getString(1) + ", semaine n°" + result.getInt(2) + " de " + result.getTime(3) + " à " + TimeTools.addTime(result.getTime(3),result.getTime(4)));
                }
                System.out.println("\n");
            }
            else
            {
                System.out.println("Cet utilisateur n'est pas un enseignant !");
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
