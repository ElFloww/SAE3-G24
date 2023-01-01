package dao;

import entities.HeureRestante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeureRestanteDAO extends DAO<HeureRestante> {

    public HeureRestanteDAO(Connection maConnexion)
    {
        super(maConnexion);
    }

    //Méthode qui permet de créer l'objet obj de la table HeureRestante
    @Override
    public boolean create(HeureRestante obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";");

            //Si il n'y a pas d'enregistrement dans la base de données
            if(!result.first())
            {
                //On insère l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "INSERT INTO heure_restante VALUES (" + obj.getId_regroupement() + "," + obj.getId_ressource() + "," + obj.getId_type_enseignement() + ",'" + obj.getNombre_heure_restante() + "')";

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

    //Méthode qui permet de supprimer l'objet obj de la table heureRestante
    public boolean delete(HeureRestante obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";");

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On supprime dans la base de données
                Statement monStatement = this.Connexion.createStatement();
                String query = "DELETE FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";";
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

    //Méthode qui permet de mettre à jour l'objet obj de la table heureRestante
    public boolean update(HeureRestante obj)
    {
        try
        {
            //On regarde si cet identifiant existe dans la base de donnée
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT id_ressource FROM heure_restante WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";");

            //S'il y a un enregistrement dans la base de données
            if(result.first())
            {
                //On met à jour l'objet
                Statement monStatement = this.Connexion.createStatement();
                String query = "UPDATE heure_restante SET id_ressource = " + obj.getId_ressource() + ", id_type_enseignement = " + obj.getId_type_enseignement() + ", id_regroupement = " + obj.getId_regroupement() + ", nombre_heure_restante = '" + obj.getNombre_heure_restante() + "' WHERE id_ressource = " + obj.getId_ressource() + " AND id_type_enseignement = " + obj.getId_type_enseignement() + " AND id_regroupement = " + obj.getId_regroupement() + ";";

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

    //Méthode qui permet de trouver une HeureRestante dans la base de données grâce à ses identifiants
    public HeureRestante find(int... parametre)
    {
        HeureRestante monHeure = new HeureRestante();
        try
        {
            //On récupère les informations de la base de données
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure_restante WHERE id_ressource = " + parametre[0] + " AND id_type_enseignement = " + parametre[1] + " AND id_regroupement = " + parametre[2] + ";");

            //Si un enregistrement existe
            if(result.first()) {
                //On met les informations récupérées dans l'objet
                monHeure = new HeureRestante(parametre[0], parametre[1], parametre[2],result.getTime("nombre_heure_restante"));
            }
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return monHeure;
    }

    //Méthode qui permet de trouver toutes les HeureRestantes dans la base de données
    @Override
    public List<HeureRestante> findAll()
    {
        //Création d'une liste d'heure restante pour le stockage des données
        List<HeureRestante> mesHeuresRestantes = new ArrayList<>();

        try
        {
            //Execution de la requête permettant la récupération de toutes les heures restantes
            ResultSet result = this.Connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM heure_restante");
            //pour chaque enregistrement du résultat de la requête
            while (result.next()) {
                //On crée un objet HeureRestante avec chaque element le composant
                HeureRestante monHeureRestante = new HeureRestante(result.getInt(1),result.getInt(2),result.getInt(3),result.getTime(4));
                //On ajoute cette HeureRestante dans la liste des HeureRestante
                mesHeuresRestantes.add(monHeureRestante);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return mesHeuresRestantes;
    }
}
