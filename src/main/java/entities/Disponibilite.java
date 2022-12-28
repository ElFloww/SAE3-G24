package entities;

public class Disponibilite {
    private int id_disponibilite;
    private byte id_jour;
    private byte id_semaine;
    private int id_heure_debut;
    private int id_heure_duree;

    public Disponibilite(int id_disponibilite, byte id_jour, byte id_semaine, int id_heure_debut, int id_heure_duree) {
        this.id_disponibilite = id_disponibilite;
        this.id_jour = id_jour;
        this.id_semaine = id_semaine;
        this.id_heure_debut = id_heure_debut;
        this.id_heure_duree = id_heure_duree;
    }
    public Disponibilite(){}

    public int getId_disponibilite() {
        return id_disponibilite;
    }

    public void setId_disponibilite(int id_disponibilite) {
        this.id_disponibilite = id_disponibilite;
    }

    public byte getId_jour() {
        return id_jour;
    }

    public void setId_jour(byte id_jour) {
        this.id_jour = id_jour;
    }

    public byte getId_semaine() {
        return id_semaine;
    }

    public void setId_semaine(byte id_semaine) {
        this.id_semaine = id_semaine;
    }

    public int getId_heure_debut() {
        return id_heure_debut;
    }

    public void setId_heure_debut(int id_heure_debut) {
        this.id_heure_debut = id_heure_debut;
    }

    public int getId_heure_duree() {
        return id_heure_duree;
    }

    public void setId_heure_duree(int id_heure_duree) {
        this.id_heure_duree = id_heure_duree;
    }

    @Override
    public String toString() {
        return "Disponibilite{" +
                "id_disponibilite=" + id_disponibilite +
                ", id_jour=" + id_jour +
                ", id_semaine=" + id_semaine +
                ", id_heure_debut=" + id_heure_debut +
                ", id_heure_duree=" + id_heure_duree +
                '}';
    }
    public boolean equals(Disponibilite obj) {
        return this.getId_disponibilite() == obj.getId_disponibilite() && this.getId_jour() == obj.getId_jour() && this.getId_semaine() == obj.getId_semaine() && this.getId_heure_debut() == obj.getId_heure_debut() && this.getId_heure_duree() == obj.getId_heure_duree();
    }
}
