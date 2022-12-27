package entities;

public class Salle {
    private int id_salle;
    private String nom_salle;
    private String type_salle;

    private int capacite_salle;

    @Override
    public String toString() {
        return "Salle{" +
                "id_salle=" + id_salle +
                ", nom_salle='" + nom_salle + '\'' +
                ", type_salle='" + type_salle + '\'' +
                ", capacite_salle=" + capacite_salle +
                '}';
    }

    public Salle(int id_salle, String nom_salle, String type_salle, int capacite_salle) {
        this.id_salle = id_salle;
        this.nom_salle = nom_salle;
        this.type_salle = type_salle;
        this.capacite_salle = capacite_salle;
    }

    public Salle(){}

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public String getType_salle() {
        return type_salle;
    }

    public void setType_salle(String type_salle) {
        this.type_salle = type_salle;
    }


    public int getCapacite_salle() {
        return capacite_salle;
    }

    public void setCapacite_salle(int capacite_salle) {
        this.capacite_salle = capacite_salle;
    }

    public boolean equals(Salle obj) {
        return this.getId_salle() == obj.getId_salle() && this.getNom_salle() == obj.getNom_salle() && this.getType_salle() == obj.getType_salle() && this.getCapacite_salle() == obj.getCapacite_salle();
    }
}
