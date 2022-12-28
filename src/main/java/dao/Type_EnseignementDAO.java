package dao;

import entities.Type_Enseignement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type_EnseignementDAO extends DAO<Type_Enseignement>{
    public Type_EnseignementDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    public boolean create(Type_Enseignement obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_type_enseignement FROM type_enseignement WHERE id_type_enseignement = " + obj.getId_type_enseignement());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO type_enseignement VALUES (" + obj.getId_type_enseignement() + ",'" + obj.getLibelle_type_enseignement() + "')";
                monStatement.executeUpdate(query);

                //On retourne vrai pour annoncé que l'action est réussie
                return true;
            }
            else
            {
                //On retourne faux pour annoncé que l'action n'est pas réussie
                return false;
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public boolean delete(Type_Enseignement obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_type_enseignement FROM type_enseignement WHERE id_type_enseignement = " + obj.getId_type_enseignement());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM type_enseignement WHERE id_type_enseignement  = " + obj.getId_type_enseignement();
                monStatement.executeUpdate(query);

                //On retourne vrai pour annoncé que l'action est réussie
                return true;
            }
            else
            {
                //On retourne faux pour annoncé que l'action n'est pas réussie
                return false;
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
    public boolean update(Type_Enseignement obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_type_enseignement FROM type_enseignement WHERE id_type_enseignement = " + obj.getId_type_enseignement());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE type_enseignement SET id_type_enseignement  = " + obj.getId_type_enseignement() + ", libelle_type_enseignement = '" + obj.getLibelle_type_enseignement() + "' WHERE id_type_enseignement = " + obj.getId_type_enseignement() + ";";
                monStatement.execute(query);

                //On retourne vrai pour annoncé que l'action est réussie
                return true;
            }
            else
            {
                //On retourne faux pour annoncé que l'action n'est pas réussie
                return false;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Type_Enseignement find(int... parametre)
    {
        Type_Enseignement enseignement = new Type_Enseignement();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_enseignement WHERE id_type_enseignement = " + parametre[0]);

            if(result.first())
            {
                enseignement = new Type_Enseignement(parametre[0],result.getString("libelle_type_enseignement"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return enseignement;
    }

}
