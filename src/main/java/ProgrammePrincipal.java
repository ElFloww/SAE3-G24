import JDBC.ConnexionBase;
import dao.DAO;
import dao.DAOFactory;
import entities.*;

import java.sql.Time;

public class ProgrammePrincipal {

    public static void main(String[] args) {


        DAO<Type_Enseignement>  enseignementdao =       DAOFactory.getType_EnseignementDAO();
        DAO<Utilisateur>        userdao =               DAOFactory.getUtilisateurDAO();
        DAO<Salle>              salledao =              DAOFactory.getSalleDAO();
        DAO<Ressource>          ressourcedao =          DAOFactory.getRessourceDAO();
        DAO<Regroupement>       regroupementdao =       DAOFactory.getRegroupementDAO();
        DAO<Jour>               jourdao =               DAOFactory.getJourDAO();
        DAO<HeureTotalPlacer>   heureaplacerdao =       DAOFactory.getHeureTotalPlacerDAO();
        DAO<HeureRestante>      heurerestantedao =      DAOFactory.getHeureRestanteDAO();
        DAO<Heure>              heuredao =              DAOFactory.getHeureDAO();
        DAO<Disponibilite>      disponibilitedao =      DAOFactory.getDisponibiliteDAO();
        DAO<DisponibiliteProf>  disponibiliteprofdao =  DAOFactory.getDisponibiliteProfDAO();
        DAO<DisponibiliteSalle> disponibiliteSalledao = DAOFactory.getDisponibiliteSalleDAO();
        DAO<Cours>              coursdao =              DAOFactory.getCoursDAO();


        ConnexionBase.DeconnexionBase();
    }
}

