package entities;

import java.sql.Time;

public class HeureTotalPlacer {
    private int id_ressource;
    private int id_type_enseignement;
    private Time total_heure;

    public HeureTotalPlacer(int id_ressource, int id_type_enseignement, Time total_heure) {
        this.id_ressource = id_ressource;
        this.id_type_enseignement = id_type_enseignement;
        this.total_heure = total_heure;
    }
    public HeureTotalPlacer(){}

    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public int getId_type_enseignement() {
        return id_type_enseignement;
    }

    public void setId_type_enseignement(int id_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
    }

    public Time getTotal_heure() {
        return total_heure;
    }

    public void setTotal_heure(Time total_heure) {
        this.total_heure = total_heure;
    }

    @Override
    public String toString() {
        return "HeureTotalPlacer{" +
                "id_ressource=" + id_ressource +
                ", id_type_enseignement=" + id_type_enseignement +
                ", total_heure=" + total_heure +
                '}';
    }
    public boolean equals(HeureTotalPlacer obj) {
        return this.getId_ressource() == obj.getId_ressource() && this.getId_type_enseignement() == obj.getId_type_enseignement() && this.getTotal_heure() == obj.getTotal_heure();
    }
}
