package entities;

public class Jour {
    private byte id_jour;
    private String nom_jour;

    public Jour(byte id_jour, String nom_jour) {
        id_jour = id_jour;
        nom_jour = nom_jour;
    }

    public byte getId_Jour() {
        return id_jour;
    }

    public String getNom_Jour() {
        return nom_jour;
    }

    public void setId_jour(byte id_jour) {
        this.id_jour = id_jour;
    }

    public void setNom_jour(String nom_jour) {
        this.nom_jour = nom_jour;
    }
}
