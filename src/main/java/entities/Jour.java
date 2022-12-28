package entities;

public class Jour {
    private byte id_jour;
    private String nom_jour;

    public Jour(byte id_jour, String nom_jour) {
        this.id_jour = id_jour;
        this.nom_jour = nom_jour;
    }

    public Jour(){}

    public byte getId_jour() {
        return id_jour;
    }

    public void setId_jour(byte id_jour) {
        this.id_jour = id_jour;
    }

    public String getNom_jour() {
        return nom_jour;
    }

    public void setNom_jour(String nom_jour) {
        this.nom_jour = nom_jour;
    }

    @Override
    public String toString() {
        return "Jour{" +
                "id_jour=" + id_jour +
                ", nom_jour='" + nom_jour + '\'' +
                '}';
    }
    public boolean equals(Jour obj) {
        return this.getId_jour() == obj.getId_jour() && this.getNom_jour() == obj.getNom_jour();
    }

}
