package dao;

import entities.Salle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO extends DAO<Salle> {

    public SalleDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table salle
    @Override
    public boolean create(Salle obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
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

    //Méthode qui permet de supprimer l'objet obj de la table salle
    public boolean delete(Salle obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM salle WHERE id_salle = " + obj.getId_salle());

            //S'il y a un enregistrement dans la base de données
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

    //Méthode qui permet de mettre à jour l'objet obj de la table salle
    public boolean update(Salle obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_salle FROM salle WHERE id_utilisateur = " + obj.getId_salle());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
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


    //Méthode qui permet de trouver une salle dans la base de données grâce à son identifiant
    public Salle find(int... parametre)
    {
        Salle maSalle = new Salle();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Salle WHERE id_salle = " + parametre[0]);

            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                maSalle = new Salle(parametre[0],result.getString("nom_salle"),result.getString("type_salle"),result.getInt("capacite_salle"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maSalle;
    }

    //Méthode qui permet de trouver toutes les salles dans la base de données
    @Override
    public List<Salle> findAll() {
        {
            //Création d'une liste de salles pour le stockage des données
            List<Salle> mesSalles = new ArrayList<>();

            try {
                //Execution de la requête permettant la récupération de toutes les salles
                ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Salle");
                //pour chaque enregistrement du résultat de la requête
                while (result.next()) {
                    //On crée un objet Salle avec chaque element le composant
                    Salle maSalle = new Salle();
                    maSalle.setId_salle(result.getInt(1));
                    maSalle.setNom_salle(result.getString(2));
                    maSalle.setType_salle(result.getString(3));
                    maSalle.setCapacite_salle(result.getInt(4));

                    //On ajoute cette salle dans la liste des salles
                    mesSalles.add(maSalle);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //On retourne la liste des salles
            return mesSalles;
        }
    }
}
