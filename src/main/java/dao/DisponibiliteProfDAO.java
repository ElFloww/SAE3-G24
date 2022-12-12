package dao;

public class DisponibiliteProfDAO {
    private int id_utilisateur;
    private int id_disponibilite;

    public DisponibiliteProfDAO(int id_utilisateur, int id_disponibilite) {
        this.id_utilisateur = id_utilisateur;
        this.id_disponibilite = id_disponibilite;
    }

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
}