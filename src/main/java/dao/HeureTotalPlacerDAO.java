package dao;

import entities.HeureTotalPlacer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeureTotalPlacerDAO extends DAO<HeureTotalPlacer> {

    public HeureTotalPlacerDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table HeureTotalAPlacer
    @Override
    public boolean create(HeureTotalPlacer obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_total_placer WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO heure_total_placer VALUES (" + obj.getId_ressource() + "," + obj.getId_type_enseignement() + ",'" + obj.getTotal_heure() + "')";

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

    //Méthode qui permet de supprimer l'objet obj de la table HeureTotalAPlacer
    public boolean delete(HeureTotalPlacer obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource,id_type_enseignement FROM heure_total_placer WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + ";");

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM heure_total_placer WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement();
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

    //Méthode qui permet de mettre à jour l'objet obj de la table HeureTotalAPlacer
    public boolean update(HeureTotalPlacer obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource,id_type_enseignement FROM heure_total_placer WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE heure_total_placer SET id_ressource = " + obj.getId_ressource() + ", id_type_enseignement = " + obj.getId_type_enseignement() + ", total_heure = '" + obj.getTotal_heure() + "' WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement();
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

    //Méthode qui permet de trouver une Heure Totale à placer dans la base de données grâce à son identifiant
    public HeureTotalPlacer find(int... parametre)
    {
        HeureTotalPlacer monHeure = new HeureTotalPlacer();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure_total_placer WHERE id_ressource = " + parametre[0] + " AND id_type_enseignement = " + parametre[1]);

            //Si un enregistrement existe
            if(result.first()) {
                //On met les informations récupérées dans l'objet
                monHeure = new HeureTotalPlacer(parametre[0], parametre[1], result.getTime("total_heure"));
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monHeure;
    }

    //Méthode qui permet de trouver toutes les heures totale à placer dans la base de données
    @Override
    public List<HeureTotalPlacer> findAll()
    {
        //Création d'une liste de HeureTotalAPlacer pour le stockage des données
        List<HeureTotalPlacer> mesHeuresAPlacer = new ArrayList<>();

        try
        {
            //Execution de la requête permettant la récupération de toutes les HeureTotalAPlacer
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure_total_placer");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet HeureTotalAPlacer avec chaque element le composant
                HeureTotalPlacer monHeureAPlacer = new HeureTotalPlacer(result.getInt(1),result.getInt(2),result.getTime(3));
                //On ajoute cette HeureTotalAPlacer dans la liste des HeureTotalAPlacers
                mesHeuresAPlacer.add(monHeureAPlacer);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne toutes les HeureTotalAPlacer
        return mesHeuresAPlacer;
    }
}
