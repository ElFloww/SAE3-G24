package dao;

import entities.Type_Enseignement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Type_EnseignementDAO extends DAO<Type_Enseignement>{
    public Type_EnseignementDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table TypeEnseignement
    public boolean create(Type_Enseignement obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_type_enseignement FROM type_enseignement WHERE id_type_enseignement = " + obj.getId_type_enseignement());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO type_enseignement VALUES (" + obj.getId_type_enseignement() + ",'" + obj.getLibelle_type_enseignement() + "')";
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

    //Méthode qui permet de supprimer l'objet obj de la table Type Enseignement
    public boolean delete(Type_Enseignement obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_type_enseignement FROM type_enseignement WHERE id_type_enseignement = " + obj.getId_type_enseignement());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM type_enseignement WHERE id_type_enseignement  = " + obj.getId_type_enseignement();
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

    //Méthode qui permet de mettre à jour l'objet obj de la table type enseignement
    public boolean update(Type_Enseignement obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_type_enseignement FROM type_enseignement WHERE id_type_enseignement = " + obj.getId_type_enseignement());

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE type_enseignement SET id_type_enseignement  = " + obj.getId_type_enseignement() + ", libelle_type_enseignement = '" + obj.getLibelle_type_enseignement() + "' WHERE id_type_enseignement = " + obj.getId_type_enseignement() + ";";
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

    //Méthode qui permet de trouver un type d'enseignement dans la base de données grâce à son identifiant
    public Type_Enseignement find(int... parametre)
    {
        Type_Enseignement enseignement = new Type_Enseignement();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_enseignement WHERE id_type_enseignement = " + parametre[0]);

            //Si un enregistrement existe
            if(result.first())
            {
                //On met les informations récupérées dans l'objet
                enseignement = new Type_Enseignement(parametre[0],result.getString("libelle_type_enseignement"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return enseignement;
    }

    //Méthode qui permet de trouver tous les Type_Enseignement dans la base de données
    @Override
    public List<Type_Enseignement> findAll()
    {
        //Création d'une liste de Type_Enseignement pour le stockage des données
        List<Type_Enseignement> mesTypeEnseignement = new ArrayList<>();
        try
        {
            //Execution de la requête permettant la récupération de toutes les Type_Enseignement
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_enseignement");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet Type_Enseignement avec chaque element le composant
                Type_Enseignement monTypeEnseignement = new Type_Enseignement(result.getInt(1),result.getString(2));
                //On ajoute ce Type_Enseignement dans la liste des Type_Enseignement
                mesTypeEnseignement.add(monTypeEnseignement);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        //On retourne tous Type_Enseignement
        return mesTypeEnseignement;
    }

}
