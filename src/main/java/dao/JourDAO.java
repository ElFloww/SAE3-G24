package dao;

import entities.Jour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JourDAO extends DAO<Jour> {

    public JourDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table jour
    @Override
    public boolean create(Jour obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_jour FROM jour WHERE id_jour = " + obj.getId_jour());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO jour VALUES (" + obj.getId_jour() + ",'" + obj.getNom_jour() + "')";
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

    //Méthode qui permet de supprimer l'objet obj de la table jour
    public boolean delete(Jour obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_jour FROM jour WHERE id_jour = " + obj.getId_jour());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM jour WHERE id_jour = " + obj.getId_jour();
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

    //Méthode qui permet de mettre à jour l'objet obj de la table jour
    public boolean update(Jour obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_jour FROM jour WHERE id_jour = " + obj.getId_jour());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE jour SET id_jour = " + obj.getId_jour() + ", nom_jour = '" + obj.getNom_jour() + "' WHERE id_jour = " + obj.getId_jour() + ";";
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

    //Méthode qui permet de trouver un jour dans la base de données grâce à son identifiant
    public Jour find(int... parametre)
    {
        Jour monJour = new Jour();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM jour WHERE id_jour = " + parametre[0]);
            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                monJour = new Jour((byte)parametre[0],result.getString("nom_jour"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monJour;
    }

    //Méthode qui permet de trouver tous les jours dans la base de données
    @Override
    public List<Jour> findAll()
    {
        //Création d'une liste de disponibilités pour le stockage des données
        List<Jour> mesJours = new ArrayList<>();

        try
        {
            //Execution de la requête permettant la récupération de tous les jours
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM jour");
            //pour chaque enregistrement du résultat de la requête

            while (result.next()) {
                //On crée un objet jour avec chaque element le composant
                Jour monJour = new Jour(result.getByte(1),result.getString(2));
                //On ajoute ce jour dans la liste des jours
                mesJours.add(monJour);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne tous les jours
        return mesJours;
    }
}
