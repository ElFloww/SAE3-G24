package dao;

import entities.Disponibilite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisponibiliteDAO extends DAO<Disponibilite> {

    public DisponibiliteDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(Disponibilite obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_disponibilite FROM disponibilite WHERE id_disponibilite = " + obj.getId_disponibilite());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO disponibilite VALUES (" + obj.getId_disponibilite() + "," + obj.getId_jour() + "," + obj.getId_semaine() + "," + obj.getId_heure_debut() + "," + obj.getId_heure_duree() + ")";
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
    public boolean delete(Disponibilite obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_disponibilite FROM disponibilite WHERE id_disponibilite = " + obj.getId_disponibilite());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM disponibilite WHERE id_disponibilite = " + obj.getId_disponibilite();
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
    public boolean update(Disponibilite obj)
    {
        try
        {
            //On regarde si cet id existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_disponibilite FROM disponibilite WHERE id_disponibilite = " + obj.getId_disponibilite());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE disponibilite SET id_disponibilite = " + obj.getId_disponibilite() + ", id_jour = " + obj.getId_jour() + ", id_semaine = " + obj.getId_semaine() + ", id_heure_debut = " + obj.getId_heure_debut() + ", id_heure_duree = " + obj.getId_heure_duree() + " WHERE id_disponibilite = " + obj.getId_disponibilite() + ";";
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
    public Disponibilite find(int... parametre)
    {
        Disponibilite maDisponibilite = new Disponibilite();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite WHERE id_disponibilite = " + parametre[0]);

            if(result.first())
            {
                maDisponibilite = new Disponibilite(parametre[0],result.getByte("id_jour"),result.getByte("id_semaine"),result.getInt("id_heure_debut"), result.getInt("id_heure_duree"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maDisponibilite;
    }

}
