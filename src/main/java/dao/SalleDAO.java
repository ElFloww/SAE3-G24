package dao;

import entities.Salle;
import entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO extends DAO<Salle> {

    public SalleDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(Salle obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM salle WHERE id_salle = " + obj.getId_salle());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO salle VALUES (" + obj.getId_salle() + ",'" + obj.getNom_salle() + "','" + obj.getType_salle() + "'," + obj.getCapacite_salle() + ")";
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
    public boolean delete(Salle obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM salle WHERE id_salle = " + obj.getId_salle());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM Salle WHERE id_salle = " + obj.getId_salle();
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
    public boolean update(Salle obj)
    {
        try
        {
            //On regarde si cet id existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM salle WHERE id_utilisateur = " + obj.getId_salle());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE salle SET id_salle = " + obj.getId_salle() + ", nom_salle = '" + obj.getNom_salle() + "' , type_salle = '" + obj.getType_salle() + "' , capacite_salle = " + obj.getCapacite_salle() + " WHERE id_utilisateur = "+ obj.getId_salle() + ";";
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
    public Salle find(int... parametre)
    {
        Salle maSalle = new Salle();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Salle WHERE id_salle = " + parametre[0]);

            if(result.first())
            {
                maSalle = new Salle(parametre[0],result.getString("nom_salle"),result.getString("type_salle"),result.getInt("capacite_salle"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maSalle;
    }

    @Override
    public List<Salle> findAll() {
        {
            List<Salle> mesSalles = new ArrayList<>();

            try {
                ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Salle");

                ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (result.next()) {
                    Salle maSalle = new Salle();
                    maSalle.setId_salle(result.getInt(1));
                    maSalle.setNom_salle(result.getString(2));
                    maSalle.setType_salle(result.getString(3));
                    maSalle.setCapacite_salle(result.getInt(4));

                    mesSalles.add(maSalle);
                    System.out.println("");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return mesSalles;
        }
    }
}
