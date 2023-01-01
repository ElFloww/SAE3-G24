package dao;

import entities.Disponibilite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisponibiliteDAO extends DAO<Disponibilite> {

    public DisponibiliteDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    //Méthode qui permet de créer l'objet obj de la table disponibilite
    @Override
    public boolean create(Disponibilite obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
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

    //Méthode qui permet de supprimer l'objet obj de la table disponibilite
    public boolean delete(Disponibilite obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_disponibilite FROM disponibilite WHERE id_disponibilite = " + obj.getId_disponibilite());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime de la base de données
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
    //Méthode qui permet de mettre à jour l'objet obj de la table disponibilite
    public boolean update(Disponibilite obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_disponibilite FROM disponibilite WHERE id_disponibilite = " + obj.getId_disponibilite());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
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

    //Méthode qui permet de trouver une disponibilité dans la base de données grâce à son identifiant
    public Disponibilite find(int... parametre)
    {
        Disponibilite maDisponibilite = new Disponibilite();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite WHERE id_disponibilite = " + parametre[0]);

            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                maDisponibilite = new Disponibilite(parametre[0],result.getByte("id_jour"),result.getByte("id_semaine"),result.getInt("id_heure_debut"), result.getInt("id_heure_duree"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maDisponibilite;
    }

    //Méthode qui permet de trouver toutes les disponibilités dans la base de données
    @Override
    public List<Disponibilite> findAll()
    {
        //Création d'une liste de disponibilités pour le stockage des données
        List<Disponibilite> mesDisponibilites = new ArrayList<>();
        try
        {
            //Execution de la requête permettant la récupération de toutes les disponibilités
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet Disponibilite avec chaque element le composant
                Disponibilite maDisponibilite = new Disponibilite(result.getInt(1),result.getByte(2),result.getByte(3),result.getInt(4),result.getInt(5));
                //On ajoute cette disponibilité dans la liste des disponibilités
                mesDisponibilites.add(maDisponibilite);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne toutes les disponibilités
        return mesDisponibilites;
    }
}
