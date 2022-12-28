package dao;

import JDBC.ConnexionBase;

import java.sql.Connection;

public class DAOFactory {
    protected static final Connection maConnexion = ConnexionBase.getInstance();
    public static DAO getUtilisateurDAO(){return new UtilisateurDAO(maConnexion);}
    public static DAO getType_EnseignementDAO(){return new Type_EnseignementDAO(maConnexion);}
    public static DAO getSalleDAO(){return new SalleDAO(maConnexion);}
    public static DAO getRessourceDAO(){return new RessourceDAO(maConnexion);}
    public static DAO getRegroupementDAO(){return new RegroupementDAO(maConnexion);}
    public static DAO getJourDAO(){return new JourDAO(maConnexion);}
    public static DAO getHeureTotalPlacerDAO(){return new HeureTotalPlacerDAO(maConnexion);}
    public static DAO getHeureRestanteDAO(){return new HeureRestanteDAO(maConnexion);}
    public static DAO getHeureDAO(){return new HeureDAO(maConnexion);}
    public static DAO getDisponibiliteDAO(){return new DisponibiliteDAO(maConnexion);}
    public static DAO getDisponibiliteProfDAO(){return new DisponibiliteProfDAO(maConnexion);}
    public static DAO getDisponibiliteSalleDAO(){return new DisponibiliteSalleDAO(maConnexion);}
    public static DAO getCoursDAO(){return new CoursDAO(maConnexion);}
}
