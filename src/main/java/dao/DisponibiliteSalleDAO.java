package dao;

public class DisponibiliteSalleDAO {
    private int id_salle;
    private int id_disponibilite;

    public DisponibiliteSalleDAO(int id_salle, int id_disponibilite) {
        this.id_salle = id_salle;
        this.id_disponibilite = id_disponibilite;
    }

    public int getId_salle() {
        return id_salle;
    }

    public int getId_disponibilite() {
        return id_disponibilite;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public void setId_disponibilite(int id_disponibilite) {
        this.id_disponibilite = id_disponibilite;
    }
}
