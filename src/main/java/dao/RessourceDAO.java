package dao;

import entities.Ressource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RessourceDAO extends DAO<Ressource> {

    public RessourceDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(Ressource obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM ressource WHERE id_ressource = " + obj.getId_ressource());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO ressource VALUES (" + obj.getId_ressource() + ",'" + obj.getCode_ressource() + "','" + obj.getNom_ressource() + "'" + ")";
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
    public boolean delete(Ressource obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM ressource WHERE id_ressource = " + obj.getId_ressource());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM ressource WHERE id_ressource = " + obj.getId_ressource();
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
    public boolean update(Ressource obj)
    {
        try
        {
            //On regarde si cet id existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM ressource WHERE id_ressource = " + obj.getId_ressource());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE ressource SET id_ressource = " + obj.getId_ressource() + ", code_ressource = '" + obj.getCode_ressource() + "' , nom_ressource = '" + obj.getNom_ressource() + "' WHERE id_ressource = " + obj.getId_ressource()+ ";";
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

    public Ressource find(int... parametre)
    {
        Ressource maRessource = new Ressource();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ressource WHERE id_ressource = " + parametre[0]);

            if(result.first())
            {
                maRessource = new Ressource(parametre[0],result.getString("code_ressource"),result.getString("nom_ressource"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maRessource;
    }

    @Override
    public List<Ressource> findAll() {
        return null;
    }

}
