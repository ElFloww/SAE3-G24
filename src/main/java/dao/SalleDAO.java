package dao;

public class SalleDAO {
    private int id_salle;
    private String nom_salle;
    private String type_salle;
    private String nom_ressource;

    public SalleDAO(int id_salle, String nom_salle, String type_salle, String nom_ressource) {
        this.id_salle = id_salle;
        this.nom_salle = nom_salle;
        this.type_salle = type_salle;
        this.nom_ressource = nom_ressource;
    }

    public int getId_salle() {
        return id_salle;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public String getType_salle() {
        return type_salle;
    }

    public String getNom_ressource() {
        return nom_ressource;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public void setType_salle(String type_salle) {
        this.type_salle = type_salle;
    }

    public void setNom_ressource(String nom_ressource) {
        this.nom_ressource = nom_ressource;
    }
}
