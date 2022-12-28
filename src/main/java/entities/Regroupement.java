package entities;

public class Regroupement {
    private int id_regroupement;
    private String description_regroupement;
    private int id_regroupement_parent;

    public Regroupement(int id_regroupement, String description_regroupement, int id_regroupement_parent) {
        this.id_regroupement = id_regroupement;
        this.description_regroupement = description_regroupement;
        this.id_regroupement_parent = id_regroupement_parent;
    }
    public Regroupement(){}

    public int getId_regroupement() {
        return id_regroupement;
    }

    public void setId_regroupement(int id_regroupement) {
        this.id_regroupement = id_regroupement;
    }

    public String getDescription_regroupement() {
        return description_regroupement;
    }

    public void setDescription_regroupement(String description_regroupement) {
        this.description_regroupement = description_regroupement;
    }

    public int getId_regroupement_parent() {
        return id_regroupement_parent;
    }

    public void setId_regroupement_parent(int id_regroupement_parent) {
        this.id_regroupement_parent = id_regroupement_parent;
    }

    @Override
    public String toString() {
        return "Regroupement{" +
                "id_regroupement=" + id_regroupement +
                ", description_regroupement='" + description_regroupement + '\'' +
                ", id_regroupement_parent=" + id_regroupement_parent +
                '}';
    }
    public boolean equals(Regroupement obj) {
        return this.getId_regroupement() == obj.getId_regroupement() && this.getDescription_regroupement() == obj.getDescription_regroupement() && this.getId_regroupement_parent() == obj.getId_regroupement_parent();
    }
}
