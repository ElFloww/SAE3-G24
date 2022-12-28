package dao;

import java.sql.Connection;
import JDBC.ConnexionBase;

public abstract class DAO<Object> {

    protected Connection Connexion = null;

    public DAO(Connection maConnexion)
    {
        this.Connexion = maConnexion;
    }

    public abstract boolean create(Object obj);
    public abstract boolean delete(Object obj);
    public abstract boolean update(Object obj);
    public abstract Object find(int ... parametre);
}
