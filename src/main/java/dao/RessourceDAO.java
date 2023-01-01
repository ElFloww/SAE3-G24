package dao;

import entities.Ressource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceDAO extends DAO<Ressource> {

    public RessourceDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table ressource
    @Override
    public boolean create(Ressource obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
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

    //Méthode qui permet de supprimer l'objet obj de la table ressource
    public boolean delete(Ressource obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM ressource WHERE id_ressource = " + obj.getId_ressource());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime de la base de données
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

    //Méthode qui permet de mettre à jour l'objet obj de la table ressource
    public boolean update(Ressource obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM ressource WHERE id_ressource = " + obj.getId_ressource());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
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

    //Méthode qui permet de trouver une ressource dans la base de données grâce à son identifiant

    public Ressource find(int... parametre)
    {
        Ressource maRessource = new Ressource();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ressource WHERE id_ressource = " + parametre[0]);

            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                maRessource = new Ressource(parametre[0],result.getString("code_ressource"),result.getString("nom_ressource"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return maRessource;
    }

    //Méthode qui permet de trouver toutes les ressources dans la base de données
    @Override
    public List<Ressource> findAll() {
        {
            //Création d'une liste de ressources pour le stockage des données
            List<Ressource> mesRessources = new ArrayList<>();

            try {
                //Execution de la requête permettant la récupération de toutes les ressources
                ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM ressource");
                //pour chaque enregistrement du résultat de la requête
                while (result.next()) {
                    //On crée un objet ressource avec chaque element le composant
                    Ressource maRessource = new Ressource();
                    maRessource.setId_ressource(result.getInt(1));
                    maRessource.setCode_ressource(result.getString(2));
                    maRessource.setNom_ressource(result.getString(3));

                    //On ajoute cette ressource dans la liste des ressources
                    mesRessources.add(maRessource);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //On retourne toutes les ressources
            return mesRessources;
        }
    }

}
