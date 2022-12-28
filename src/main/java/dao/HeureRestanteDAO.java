package dao;

import entities.HeureRestante;

import java.sql.*;

public class HeureRestanteDAO extends DAO<HeureRestante> {

    public HeureRestanteDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(HeureRestante obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO heure_restante VALUES (" + obj.getId_regroupement() + "," + obj.getId_ressource() + "," + obj.getId_type_enseignement() + ",'" + obj.getNombre_heure_restante() + "')";

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
    public boolean delete(HeureRestante obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";";
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
    public boolean update(HeureRestante obj)
    {
        try
        {
            //On regarde si cet id existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE heure_restante SET id_ressource = " + obj.getId_ressource() + ", id_type_enseignement = " + obj.getId_type_enseignement() + ", id_regroupement = " + obj.getId_regroupement() + ", nombre_heure_restante = '" + obj.getNombre_heure_restante() + "' WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";";

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

    public HeureRestante find(int... parametre)
    {
        HeureRestante monHeure = new HeureRestante();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure_restante WHERE id_ressource = " + parametre[0] + " AND id_type_enseignement = " + parametre[1] + " AND id_regroupement = " + parametre[2] + ";");

            if(result.first()) {
                monHeure = new HeureRestante(parametre[0], parametre[1], parametre[2],result.getTime("nombre_heure_restante"));
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monHeure;
    }
}
