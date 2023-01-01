package dao;

import entities.DisponibiliteSalle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisponibiliteSalleDAO extends DAO<DisponibiliteSalle> {

    public DisponibiliteSalleDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table disponibilitesalle
    @Override
    public boolean create(DisponibiliteSalle obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
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

    //Méthode qui permet de supprimer l'objet obj de la table disponibiliteSalle
    public boolean delete(DisponibiliteSalle obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM disponibilite_salle WHERE id_salle = " + obj.getId_salle() + " AND id_disponibilite = " + obj.getId_disponibilite());

            //S'il y a un enregistrement dans la base de données
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

    //Méthode qui permet de mettre à jour l'objet obj de la table disponibiliteSalle
    public boolean update(DisponibiliteSalle obj)
    {
        //L'update est inexistant pour cette table, car tous les champs constitue la cle primaire
        return true;
    }

    //Méthode qui permet de trouver une disponibilité de salle dans la base de données grâce à son identifiant
    public DisponibiliteSalle find(int... parametre)
    {
        DisponibiliteSalle maDisponibiliteSalle = new DisponibiliteSalle();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite_salle WHERE id_salle = " + parametre[0] + " AND id_disponibilite = " + parametre[1]);

            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                maDisponibiliteSalle = new DisponibiliteSalle(parametre[0],parametre[1]);
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maDisponibiliteSalle;
    }

    //Méthode qui permet de trouver toutes les disponibilités des salles dans la base de données
    @Override
    public List<DisponibiliteSalle> findAll()
    {
        //Création d'une liste de disponibilitésSalle pour le stockage des données
        List<DisponibiliteSalle> mesDisponibiliteSalle = new ArrayList<>();

        try
        {
            //Execution de la requête permettant la récupération de toutes les disponibilités des salles
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM disponibilite_salle");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet DisponibiliteSalle avec chaque element le composant
                DisponibiliteSalle maDisponibiliteSalle = new DisponibiliteSalle(result.getInt(1),result.getInt(2));
                //On ajoute cette disponibilitéSalle dans la liste des disponibilitésSalle
                mesDisponibiliteSalle.add(maDisponibiliteSalle);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne toutes les disponibilités
        return mesDisponibiliteSalle;
    }

}
