package dao;

import entities.HeureTotalPlacer;

import java.sql.*;
import java.util.List;

public class HeureTotalPlacerDAO extends DAO<HeureTotalPlacer> {

    public HeureTotalPlacerDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(HeureTotalPlacer obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
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
    public boolean delete(HeureTotalPlacer obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource,id_type_enseignement FROM heure_total_placer WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
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
    public boolean update(HeureTotalPlacer obj)
    {
        try
        {
            //On regarde si cet id existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource,id_type_enseignement FROM heure_total_placer WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
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

    public HeureTotalPlacer find(int... parametre)
    {
        HeureTotalPlacer monHeure = new HeureTotalPlacer();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure_total_placer WHERE id_ressource = " + parametre[0] + " AND id_type_enseignement = " + parametre[1]);

            if(result.first()) {
                monHeure = new HeureTotalPlacer(parametre[0], parametre[1], result.getTime("total_heure"));
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monHeure;
    }

    @Override
    public List<HeureTotalPlacer> findAll() {
        return null;
    }
}
