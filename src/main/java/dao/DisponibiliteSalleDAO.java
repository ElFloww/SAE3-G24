package dao;

import entities.DisponibiliteSalle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisponibiliteSalleDAO extends DAO<DisponibiliteSalle> {

    public DisponibiliteSalleDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(DisponibiliteSalle obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM disponibilite_salle WHERE id_salle = " + obj.getId_salle() + " AND id_disponibilite = " + obj.getId_disponibilite());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO disponibilite_salle VALUES (" + obj.getId_salle() + "," + obj.getId_disponibilite() + ")";
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
    public boolean delete(DisponibiliteSalle obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM disponibilite_salle WHERE id_salle = " + obj.getId_salle() + " AND id_disponibilite = " + obj.getId_disponibilite());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM disponibilite_salle WHERE id_salle = " + obj.getId_salle() + " AND id_disponibilite = " + obj.getId_disponibilite();
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
    public boolean update(DisponibiliteSalle obj)
    {
        //L'update est inexistant pour cette table, car tous les champs constitue la cle primaire
        return true;
    }
    public DisponibiliteSalle find(int... parametre)
    {
        DisponibiliteSalle maDisponibiliteSalle = new DisponibiliteSalle();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite_salle WHERE id_salle = " + parametre[0] + " AND id_disponibilite = " + parametre[1]);

            if(result.first())
            {
                maDisponibiliteSalle = new DisponibiliteSalle(parametre[0],parametre[1]);
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maDisponibiliteSalle;
    }

}
