package entities;

public class Cours{

    private int id_utilisateur;
    private int id_regroupement;
    private int id_salle;
    private int id_ressource;
    private int id_heure_debut;
    private int id_heure_duree;
    private byte id_semaine;
    private byte id_jours;
    private int id_type_enseignement;

    public Cours(int id_utilisateur, int id_regroupement, int id_salle, int id_ressource, int id_heure_debut, int id_heure_duree, byte id_semaine, byte id_jours, int id_type_enseignement) {
        this.id_utilisateur = id_utilisateur;
        this.id_regroupement = id_regroupement;
        this.id_salle = id_salle;
        this.id_ressource = id_ressource;
        this.id_heure_debut = id_heure_debut;
        this.id_heure_duree = id_heure_duree;
        this.id_semaine = id_semaine;
        this.id_jours = id_jours;
        this.id_type_enseignement = id_type_enseignement;
    }

    public Cours(){}

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_regroupement() {
        return id_regroupement;
    }

    public void setId_regroupement(int id_regroupement) {
        this.id_regroupement = id_regroupement;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
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

    public byte getId_semaine() {
        return id_semaine;
    }

    public void setId_semaine(byte id_semaine) {
        this.id_semaine = id_semaine;
    }

    public byte getId_jours() {
        return id_jours;
    }

    public void setId_jours(byte id_jours) {
        this.id_jours = id_jours;
    }

    public int getId_type_enseignement() {
        return id_type_enseignement;
    }

    public void setId_type_enseignement(int id_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id_utilisateur=" + id_utilisateur +
                ", id_regroupement=" + id_regroupement +
                ", id_salle=" + id_salle +
                ", id_ressource=" + id_ressource +
                ", id_heure_debut=" + id_heure_debut +
                ", id_heure_duree=" + id_heure_duree +
                ", id_semaine=" + id_semaine +
                ", id_jours=" + id_jours +
                ", id_type_enseignement=" + id_type_enseignement +
                '}';
    }
    public boolean equals(Cours obj) {
        return this.getId_utilisateur() == obj.getId_utilisateur() && this.getId_regroupement() == obj.getId_regroupement() && this.getId_salle() == obj.getId_salle() && this.getId_ressource() == obj.getId_ressource() && this.getId_heure_debut() == obj.getId_heure_debut() && this.getId_heure_duree() == obj.getId_heure_duree() && this.getId_semaine() == obj.getId_semaine() && this.getId_jours() == obj.getId_jours() && this.getId_type_enseignement() == obj.getId_type_enseignement();
    }

}