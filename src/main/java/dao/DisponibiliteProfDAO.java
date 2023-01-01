package dao;

import entities.DisponibiliteProf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisponibiliteProfDAO extends DAO<DisponibiliteProf> {

    public DisponibiliteProfDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table DisponibiliteProf
    @Override
    public boolean create(DisponibiliteProf obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM disponibilite_prof WHERE id_utilisateur = " + obj.getId_utilisateur() + " AND id_disponibilite = " + obj.getId_disponibilite());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO disponibilite_prof VALUES (" + obj.getId_utilisateur() + "," + obj.getId_disponibilite() + ")";
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

    //Méthode qui permet de supprimer l'objet obj de la table disponibiliteProf
    @Override
    public boolean delete(DisponibiliteProf obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM disponibilite_prof WHERE id_utilisateur = " + obj.getId_utilisateur() + " AND id_disponibilite = " + obj.getId_disponibilite());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM disponibilite_prof WHERE id_utilisateur = " + obj.getId_utilisateur() + " AND id_disponibilite = " + obj.getId_disponibilite();
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
    //Méthode qui permet de mettre à jour l'objet obj de la table disponibiliteprof
    @Override
    public boolean update(DisponibiliteProf obj)
    {
        //L'update est inexistant pour cette table, car tous les champs constitue la cle primaire
        return true;
    }
    //Méthode qui permet de trouver une disponibilité de professeurs dans la base de données grâce à son identifiant
    @Override
    public DisponibiliteProf find(int... parametre)
    {
        DisponibiliteProf maDisponibiliteProf = new DisponibiliteProf();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite_prof WHERE id_utilisateur = " + parametre[0] + " AND id_disponibilite = " + parametre[1]);

            if(result.first())
            {
                maDisponibiliteProf = new DisponibiliteProf(parametre[0],parametre[1]);
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maDisponibiliteProf;
    }

    //Méthode qui permet de trouver toutes les disponibilités de professeurs dans la base de données
    @Override
    public List<DisponibiliteProf> findAll()
    {
        //Création d'une liste de disponibilitésProf pour le stockage des données
        List<DisponibiliteProf> mesDisponibiliteProf = new ArrayList<>();
        try
        {
            //Execution de la requête permettant la récupération de toutes les disponibilités des professeurs
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite_prof");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet DisponibiliteProf avec chaque element le composant
                DisponibiliteProf maDisponibiliteProf = new DisponibiliteProf(result.getInt(1),result.getInt(2));
                //On ajoute cette disponibilitéProf dans la liste des disponibilitésProf
                mesDisponibiliteProf.add(maDisponibiliteProf);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne toutes les disponibilités
        return mesDisponibiliteProf;
    }
}
