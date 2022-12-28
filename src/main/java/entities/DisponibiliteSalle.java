package entities;

public class DisponibiliteSalle {
    private int id_salle;
    private int id_disponibilite;

    public DisponibiliteSalle(int id_salle, int id_disponibilite) {
        this.id_salle = id_salle;
        this.id_disponibilite = id_disponibilite;
    }
    public DisponibiliteSalle(){}

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

    @Override
    public String toString() {
        return "DisponibiliteSalle{" +
                "id_salle=" + id_salle +
                ", id_disponibilite=" + id_disponibilite +
                '}';
    }

    public boolean equals(DisponibiliteSalle obj) {
        return this.getId_disponibilite() == obj.getId_disponibilite() && this.getId_salle() == obj.getId_salle();
    }
}
