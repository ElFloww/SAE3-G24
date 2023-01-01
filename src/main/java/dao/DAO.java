package dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<Object> {

    protected Connection Connexion = null;

    public DAO(Connection maConnexion)
    {
        this.Connexion = maConnexion;
    }

    //Méthode qui permet de créer l'objet obj de la base de donnée
    public abstract boolean create(Object obj);
    //Méthode qui permet de supprimer l'objet obj de la base de donnée
    public abstract boolean delete(Object obj);
    //Méthode qui permet de modifier l'objet obj de la base de donnée
    public abstract boolean update(Object obj);
    //Méthode qui permet de trouver l'objet obj de la base de donnée
    public abstract Object find(int ... parametre);
    //Méthode qui permet de trouver tous les objets de la base de donnée
    public abstract List<Object> findAll();
}
