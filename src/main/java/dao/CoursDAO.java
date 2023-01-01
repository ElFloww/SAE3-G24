package dao;

import entities.Cours;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours> {

    //Constructeur qui appelle le constructeur de DAO
    public CoursDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table cours
    @Override
    public boolean create(Cours obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM Cours WHERE id_utilisateur = " + obj.getId_utilisateur() + " AND id_regroupement = " + obj.getId_regroupement() + " AND id_salle = " + obj.getId_salle() + " AND id_ressource = " + obj.getId_ressource() + " AND id_heure_debut = " + obj.getId_heure_debut() + " AND id_heure_duree = " + obj.getId_heure_duree() + " AND id_semaine = " + obj.getId_semaine() + " AND id_jour = " + obj.getId_jours() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO Cours VALUES (" + obj.getId_utilisateur() + " , " + obj.getId_regroupement() + " , " + obj.getId_salle() + " , " + obj.getId_ressource() + " , " + obj.getId_heure_debut() + " , " + obj.getId_heure_duree() + " , " + obj.getId_semaine() + " , " + obj.getId_jours() + " , " + obj.getId_type_enseignement() + ");";

                monStatement.executeUpdate(query);

                //On retourne vrai pour annoncer que l'action est réussie
                return true;
            }
            else
            {
                //On retourne faux pour annoncer que l'action n'est pas réussie
                return false;
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Méthode qui permet de supprimer l'objet obj de la table cours
    public boolean delete(Cours obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM Cours WHERE id_utilisateur = " + obj.getId_utilisateur() + " AND id_regroupement = " + obj.getId_regroupement() + " AND id_salle = " + obj.getId_salle() + " AND id_ressource = " + obj.getId_ressource() + " AND id_heure_debut = " + obj.getId_heure_debut() + " AND id_heure_duree = " + obj.getId_heure_duree() + " AND id_semaine = " + obj.getId_semaine() + " AND id_jour = " + obj.getId_jours() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM cours WHERE id_utilisateur = " + obj.getId_utilisateur() + " AND id_regroupement = " + obj.getId_regroupement() + " AND id_salle = " + obj.getId_salle() + " AND id_ressource = " + obj.getId_ressource() + " AND id_heure_debut = " + obj.getId_heure_debut() + " AND id_heure_duree = " + obj.getId_heure_duree() + " AND id_semaine = " + obj.getId_semaine() + " AND id_jour = " + obj.getId_jours() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + ";";
                monStatement.executeUpdate(query);

                //On retourne vrai pour annoncer que l'action est réussie
                return true;
            }
            else
            {
                //On retourne faux pour annoncer que l'action n'est pas réussie
                return false;
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    //Méthode qui permet de mettre à jour l'objet obj de la table cours
    public boolean update(Cours obj)
    {
        //L'update est inexistant pour cette table, car tous les champs constitue la cle primaire
        return true;
    }
    //Méthode qui permet de trouver un cours dans la base de données grâce à son identifiant
    public Cours find(int... parametre)
    {
        Cours monCours = new Cours();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Cours WHERE id_utilisateur = " + parametre[0] + " AND id_regroupement = " + parametre[1] + " AND id_salle = " + parametre[2] + " AND id_ressource = " + parametre[3] + " AND id_heure_debut = " + parametre[4] + " AND id_heure_duree = " + parametre[5] + " AND id_semaine = " + parametre[6] + " AND id_jour = " + parametre[7] + " AND id_type_enseignement = " + parametre[8] + ";");

            if(result.first())
            {
                monCours = new Cours(parametre[0],parametre[1],parametre[2],parametre[3],parametre[4],parametre[5],(byte)parametre[6],(byte)parametre[7],parametre[8]);
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monCours;
    }

    //Méthode qui permet de trouver tous les cours dans la base de données
    @Override
    public List<Cours> findAll()
    {
        List<Cours> mesCours = new ArrayList<>();

        try
        {
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM cours");
            while (result.next()) {
                Cours monCours = new Cours();
                monCours.setId_utilisateur(result.getInt(1));
                monCours.setId_regroupement(result.getInt(2));
                monCours.setId_salle(result.getInt(3));
                monCours.setId_ressource(result.getInt(4));
                monCours.setId_heure_debut(result.getInt(5));
                monCours.setId_heure_duree(result.getInt(6));
                monCours.setId_semaine(result.getByte(7));
                monCours.setId_jours(result.getByte(8));
                monCours.setId_type_enseignement(result.getInt(9));
                mesCours.add(monCours);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return mesCours;
    }
}
