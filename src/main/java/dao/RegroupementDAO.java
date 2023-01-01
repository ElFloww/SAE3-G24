package dao;

import entities.Regroupement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegroupementDAO extends DAO<Regroupement> {

    public RegroupementDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table regroupement
    @Override
    public boolean create(Regroupement obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_regroupement FROM regroupement WHERE id_regroupement = " + obj.getId_regroupement());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO regroupement VALUES (" + obj.getId_regroupement() + ",'" + obj.getDescription_regroupement() + "'," + obj.getId_regroupement_parent() + ")";
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

    //Méthode qui permet de supprimer l'objet obj de la table regroupement
    public boolean delete(Regroupement obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_regroupement FROM regroupement WHERE id_regroupement = " + obj.getId_regroupement());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM regroupement WHERE id_regroupement = " + obj.getId_regroupement();
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

    //Méthode qui permet de mettre à jour l'objet obj de la table regroupement
    public boolean update(Regroupement obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_regroupement FROM regroupement WHERE id_regroupement = " + obj.getId_regroupement());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE regroupement SET id_regroupement = " + obj.getId_regroupement() + ", description_regroupement = '" + obj.getDescription_regroupement() + "' , id_regroupement_parent = '" + obj.getId_regroupement_parent() + "' WHERE id_regroupement = " + obj.getId_regroupement()+ ";";
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

    //Méthode qui permet de mettre à jour l'objet obj de la table regroupement
    public Regroupement find(int... parametre)
    {
        Regroupement monRegroupement = new Regroupement();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM regroupement WHERE id_regroupement = " + parametre[0]);
            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                monRegroupement = new Regroupement(parametre[0],result.getString("description_regroupement"),result.getInt("id_regroupement_parent"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monRegroupement;
    }

    //Méthode qui permet de trouver tous les regroupements dans la base de données
    @Override
    public List<Regroupement> findAll()
    {
        //Création d'une liste de regroupement pour le stockage des données
        List<Regroupement> mesRegroupements = new ArrayList<>();

        try{
            //Execution de la requête permettant la récupération de tous les regroupements
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM regroupement");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet regroupement avec chaque element le composant
                Regroupement monRegroupement = new Regroupement();
                monRegroupement.setId_regroupement(result.getInt(1));
                monRegroupement.setDescription_regroupement(result.getString(2));
                monRegroupement.setId_regroupement_parent(result.getInt(3));

                //On ajoute ce regroupement dans la liste des regroupements
                mesRegroupements.add(monRegroupement);
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne tous les regroupements
        return mesRegroupements;
    }
}
