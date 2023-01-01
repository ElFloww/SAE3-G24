package dao;

import entities.Heure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HeureDAO extends DAO<Heure> {

    public HeureDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    //Méthode qui permet de créer l'objet obj de la table heure
    @Override
    public boolean create(Heure obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_heure FROM heure WHERE id_heure = " + obj.getId_Heure());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO Heure VALUES (" + obj.getId_Heure() + ",'" + obj.getHeure() + "')";
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

    //Méthode qui permet de supprimer l'objet obj de la table heure
    public boolean delete(Heure obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_heure FROM heure WHERE id_heure = " + obj.getId_Heure());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM heure WHERE id_heure = " + obj.getId_Heure();
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

    //Méthode qui permet de mettre à jour l'objet obj de la table heure
    public boolean update(Heure obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_heure FROM heure WHERE id_heure = " + obj.getId_Heure());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE heure SET id_heure = " + obj.getId_Heure() + ", heure = '" + obj.getHeure() + "' WHERE id_heure = " + obj.getId_Heure() + ";";
                monStatement.execute(query);

                //On retourne vrai pour annoncer que l'action est réussie
                return true;
            }
            else
            {
                //On retourne faux pour annoncer que l'action n'est pas réussie
                return false;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Méthode qui permet de trouver une heure dans la base de données grâce à son identifiant
    public Heure find(int... parametre)
    {
        Heure monHeure = new Heure();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure WHERE id_heure = " + parametre[0]);

            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                monHeure = new Heure(parametre[0],result.getTime("heure"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monHeure;
    }

    //Méthode qui permet de trouver toutes les heures dans la base de données
    @Override
    public List<Heure> findAll()
    {
        //Création d'une liste d'heure pour le stockage des données
        List<Heure> mesHeures = new ArrayList<>();
        try
        {
            //Execution de la requête permettant la récupération de toutes les disponibilités
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet heure avec chaque element le composant
                Heure monHeure = new Heure(result.getInt(1),result.getTime(2));
                //On ajoute cette heure dans la liste des heures
                mesHeures.add(monHeure);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne toutes les disponibilités
        return mesHeures;
    }

}
