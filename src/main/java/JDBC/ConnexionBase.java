package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnexionBase {

    private static String nomDriver = "com.mysql.cj.jdbc.Driver";
    private static String urlBD = "jdbc:mysql://79.87.121.17:3306/baseedt";
    private static String user = "bdsae";
    private static String password = "bdsae";
    public static Connection maConnexion;

    public static Connection getInstance() {
        //Si la connexion n'est pas initialisée
        if (maConnexion == null) {
            try {
                //Chargement du driver en mémoire
                Class.forName(nomDriver);
            } catch (ClassNotFoundException e) {
                //on gère l'exception comme elle n'est pas issue de la classe RuntimeException
                e.printStackTrace();
                System.exit(-1);
            }
            try {
                //On fait la connexion avec le serveur
                maConnexion = DriverManager.getConnection(urlBD, user, password);
                System.out.println("La connexion au serveur est réussie");
            } catch (SQLException e) { //de même, on gère l'exception
                throw new RuntimeException(e);
            }
        }
        //On retourne la connexion
        return maConnexion;
    }

    public static void DeconnexionBase()
    {
        try
        {
            //fermeture de la connexion
            maConnexion.close();
            System.out.println("Connexion fermée.");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void CreationTable() throws SQLException
    {
        //création des tables
        List<String> listeRequetes = new ArrayList<String>();

        listeRequetes.add("CREATE TABLE IF NOT EXISTS Regroupement(\n" +
                "   id_regroupement INT AUTO_INCREMENT,\n" +
                "   description_regroupement VARCHAR(30),\n" +
                "   id_regroupement_parent INT,\n" +
                "   PRIMARY KEY(id_regroupement),\n" +
                "   FOREIGN KEY(id_regroupement_parent) REFERENCES Regroupement(id_regroupement)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Salle(\n" +
                "   id_salle INT AUTO_INCREMENT,\n" +
                "   nom_salle VARCHAR(20),\n" +
                "   type_salle VARCHAR(5),\n" +
                "   capacite_salle INT,\n" +
                "   PRIMARY KEY(id_salle)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Ressource(\n" +
                "   id_ressource INT AUTO_INCREMENT,\n" +
                "   code_ressource VARCHAR(6),\n" +
                "   nom_ressource VARCHAR(100),\n" +
                "   PRIMARY KEY(id_ressource)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Heure(\n" +
                "   id_heure INT AUTO_INCREMENT,\n" +
                "   heure TIME,\n" +
                "   PRIMARY KEY(id_heure)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Jour(\n" +
                "   id_jour TINYINT AUTO_INCREMENT,\n" +
                "   nom_jour VARCHAR(10),\n" +
                "   PRIMARY KEY(id_jour)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Type_Enseignement(\n" +
                "   id_type_enseignement INT AUTO_INCREMENT,\n" +
                "   libelle_type_enseignement VARCHAR(20),\n" +
                "   PRIMARY KEY(id_type_enseignement)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Utilisateur(\n" +
                "   id_utilisateur INT AUTO_INCREMENT,\n" +
                "   nom VARCHAR(30),\n" +
                "   prenom VARCHAR(30),\n" +
                "   statut VARCHAR(15),\n" +
                "   mdp_crypte VARCHAR(50),\n" +
                "   id_regroupement INT,\n" +
                "   PRIMARY KEY(id_utilisateur),\n" +
                "   FOREIGN KEY(id_regroupement) REFERENCES Regroupement(id_regroupement)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Disponibilite(\n" +
                "   id_disponibilite INT AUTO_INCREMENT,\n" +
                "   id_jour TINYINT NOT NULL,\n" +
                "   id_semaine TINYINT NOT NULL,\n" +
                "   id_heure_debut INT NOT NULL,\n" +
                "   id_heure_duree INT NOT NULL,\n" +
                "   PRIMARY KEY(id_disponibilite),\n" +
                "   FOREIGN KEY(id_jour) REFERENCES Jour(id_jour),\n" +
                "   FOREIGN KEY(id_heure_debut) REFERENCES Heure(id_heure),\n" +
                "   FOREIGN KEY(id_heure_duree) REFERENCES Heure(id_heure)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Heure_Restante(\n" +
                "   id_regroupement INT,\n" +
                "   id_ressource INT,\n" +
                "   id_type_enseignement INT,\n" +
                "   nombre_heure_restante TIME,\n" +
                "   PRIMARY KEY(id_regroupement, id_ressource, id_type_enseignement),\n" +
                "   FOREIGN KEY(id_regroupement) REFERENCES Regroupement(id_regroupement),\n" +
                "   FOREIGN KEY(id_ressource) REFERENCES Ressource(id_ressource),\n" +
                "   FOREIGN KEY(id_type_enseignement) REFERENCES Type_Enseignement(id_type_enseignement)\n" +
                ");");
        //c'est celle juste en-dessous qui plante (cannot add foreign key constraint)
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Disponibilite_Prof(\n" +
                "   id_utilisateur INT,\n" +
                "   id_disponibilite INT,\n" +
                "   PRIMARY KEY(id_utilisateur, id_disponibilite),\n" +
                "   FOREIGN KEY(id_utilisateur) REFERENCES Utilisateur(id_utilisateur),\n" +
                "   FOREIGN KEY(id_disponibilite) REFERENCES Disponibilite(id_disponibilite)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Disponibilite_Salle(\n" +
                "   id_salle INT,\n" +
                "   id_disponibilite INT,\n" +
                "   PRIMARY KEY(id_salle, id_disponibilite),\n" +
                "   FOREIGN KEY(id_salle) REFERENCES Salle(id_salle),\n" +
                "   FOREIGN KEY(id_disponibilite) REFERENCES Disponibilite(id_disponibilite)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Cours(\n" +
                "   id_utilisateur INT,\n" +
                "   id_regroupement INT,\n" +
                "   id_salle INT,\n" +
                "   id_ressource INT,\n" +
                "   id_heure_debut INT,\n" +
                "   id_heure_duree INT,\n" +
                "   id_semaine TINYINT,\n" +
                "   id_jour TINYINT,\n" +
                "   id_type_enseignement INT,\n" +
                "   PRIMARY KEY(id_utilisateur, id_regroupement, id_salle, id_ressource, id_heure_debut, id_heure_duree, id_semaine, id_jour, id_type_enseignement),\n" +
                "   FOREIGN KEY(id_utilisateur) REFERENCES Utilisateur(id_utilisateur),\n" +
                "   FOREIGN KEY(id_regroupement) REFERENCES Regroupement(id_regroupement),\n" +
                "   FOREIGN KEY(id_salle) REFERENCES Salle(id_salle),\n" +
                "   FOREIGN KEY(id_ressource) REFERENCES Ressource(id_ressource),\n" +
                "   FOREIGN KEY(id_heure_debut) REFERENCES Heure(id_heure),\n" +
                "   FOREIGN KEY(id_heure_duree) REFERENCES Heure(id_heure),\n" +
                "   FOREIGN KEY(id_jour) REFERENCES Jour(id_jour),\n" +
                "   FOREIGN KEY(id_type_enseignement) REFERENCES Type_Enseignement(id_type_enseignement)\n" +
                ");");
        listeRequetes.add("CREATE TABLE IF NOT EXISTS Heure_Total_Placer(\n" +
                "   id_ressource INT,\n" +
                "   id_type_enseignement INT,\n" +
                "   total_heure TIME,\n" +
                "   PRIMARY KEY(id_ressource, id_type_enseignement),\n" +
                "   FOREIGN KEY(id_ressource) REFERENCES Ressource(id_ressource),\n" +
                "   FOREIGN KEY(id_type_enseignement) REFERENCES Type_Enseignement(id_type_enseignement)\n" +
                ");");

        Statement monStatement = null;
        monStatement = this.maConnexion.createStatement();

        for (String requete : listeRequetes) {
            int res = monStatement.executeUpdate(requete);
            System.out.println(res);
        }
        //fermeture du statement
        monStatement.close();
    }
}
