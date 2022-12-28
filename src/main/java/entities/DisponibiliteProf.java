package entities;

public class DisponibiliteProf {
    private int id_utilisateur;
    private int id_disponibilite;

    public DisponibiliteProf(int id_utilisateur, int id_disponibilite) {
        this.id_utilisateur = id_utilisateur;
        this.id_disponibilite = id_disponibilite;
    }

    public DisponibiliteProf(){}

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getId_disponibilite() {
        return id_disponibilite;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_disponibilite(int id_disponibilite) {
        this.id_disponibilite = id_disponibilite;
    }

    @Override
    public String toString() {
        return "DisponibiliteProf{" +
                "id_utilisateur=" + id_utilisateur +
                ", id_disponibilite=" + id_disponibilite +
                '}';
    }
    public boolean equals(DisponibiliteProf obj) {
        return this.getId_utilisateur() == obj.getId_utilisateur() && this.getId_disponibilite() == obj.getId_disponibilite();
    }


}