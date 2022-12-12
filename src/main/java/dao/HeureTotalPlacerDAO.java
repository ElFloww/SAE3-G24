package dao;

public class HeureTotalPlacerDAO {
    private int id_ressource;
    private int id_type_enseignement;
    private int total_heure;

    public HeureTotalPlacerDAO(int id_ressource, int id_type_enseignement, int total_heure) {
        this.id_ressource = id_ressource;
        this.id_type_enseignement = id_type_enseignement;
        this.total_heure = total_heure;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public int getId_type_enseignement() {
        return id_type_enseignement;
    }

    public int getTotal_heure() {
        return total_heure;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public void setId_type_enseignement(int id_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
    }

    public void setTotal_heure(int total_heure) {
        this.total_heure = total_heure;
    }
}
