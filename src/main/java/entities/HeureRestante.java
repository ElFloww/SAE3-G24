package entities;

public class HeureRestante {
    private int id_regroupement;
    private int id_ressource;
    private int id_type_enseignement;
    private int nombre_heure_restante;

    public HeureRestante(int id_regroupement, int id_ressource, int id_type_enseignement, int nombre_heure_restante) {
        this.id_regroupement = id_regroupement;
        this.id_ressource = id_ressource;
        this.id_type_enseignement = id_type_enseignement;
        this.nombre_heure_restante = nombre_heure_restante;
    }

    public int getId_regroupement() {
        return id_regroupement;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public int getId_type_enseignement() {
        return id_type_enseignement;
    }

    public int getNombre_heure_restante() {
        return nombre_heure_restante;
    }

    public void setId_regroupement(int id_regroupement) {
        this.id_regroupement = id_regroupement;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public void setId_type_enseignement(int id_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
    }

    public void setNombre_heure_restante(int nombre_heure_restante) {
        this.nombre_heure_restante = nombre_heure_restante;
    }
}
