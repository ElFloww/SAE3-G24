package dao;

import JDBC.ConnexionBase;
import entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur> {

    public UtilisateurDAO(Connection maConnexion)
    {
        super(maConnexion);
    }
    @Override
    public boolean create(Utilisateur obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM Utilisateur WHERE id_utilisateur = " + obj.getId_utilisateur());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO Utilisateur VALUES (" + obj.getId_utilisateur() + ",'" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getStatut() + "','" + obj.getMdp_crypte() + "'," + obj.getId_regroupement() +");";

                System.out.println(query);
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
    public boolean delete(Utilisateur obj)
    {
        try
        {
            //On regarde" si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM Utilisateur WHERE id_utilisateur = " + obj.getId_utilisateur());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM Utilisateur WHERE id_utilisateur = " + obj.getId_utilisateur();
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
    public boolean update(Utilisateur obj)
    {
        try
        {
            //On regarde si cet id_utilisateur existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_utilisateur FROM Utilisateur WHERE id_utilisateur = " + obj.getId_utilisateur());

            //Si il n'y a pas d'enregistrement dans la base de données
            if(result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE Utilisateur SET id_utilisateur = " + obj.getId_utilisateur() + ", nom = '" + obj.getNom() + "' , prenom = '" + obj.getPrenom() + "' , statut = '" + obj.getStatut() + "' , mdp_crypte = '" + obj.getMdp_crypte() + "' , id_regroupement = " + obj.getId_regroupement() + " WHERE id_utilisateur = "+ obj.getId_utilisateur() + ";";
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
    public Utilisateur find(int... parametre)
    {
        Utilisateur user = new Utilisateur();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Utilisateur WHERE id_utilisateur = " + parametre[0]);

            if(result.first())
            {
                user = new Utilisateur(parametre[0],result.getString("nom"),result.getString("prenom"),result.getString("statut"),result.getString("mdp_crypte"),result.getInt("id_regroupement"));
            }

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<Utilisateur> findAll()
    {
        List<Utilisateur> mesUtilisateurs = new ArrayList<>();

        try{
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Utilisateur");

            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                Utilisateur monUtilisateur = new Utilisateur();
                monUtilisateur.setId_utilisateur(result.getInt(1));
                monUtilisateur.setNom(result.getString(2));
                monUtilisateur.setPrenom(result.getString(3));
                monUtilisateur.setStatut(result.getString(4));
                monUtilisateur.setMdp_crypte(result.getString(5));
                monUtilisateur.setId_regroupement(result.getInt(6));

                mesUtilisateurs.add(monUtilisateur);
                System.out.println("");
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return mesUtilisateurs;
    }

}
