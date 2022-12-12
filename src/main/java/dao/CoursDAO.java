package dao;

public class CoursDAO {

    private int id_cours;
    private String jour;
    private int semaine;
    private String id_heure_debut;
    private String id_heure_fin;

    public CoursDAO(int id_cours, String jour, int semaine, String id_heure_debut, String id_heure_fin) {
        this.id_cours = id_cours;
        this.jour = jour;
        this.semaine = semaine;
        this.id_heure_debut = id_heure_debut;
        this.id_heure_fin = id_heure_fin;
    }

    public int getId_cours() {
        return id_cours;
    }

    public String getJour() {
        return jour;
    }

    public int getSemaine() {
        return semaine;
    }

    public String getId_heure_debut() {
        return id_heure_debut;
    }

    public String getId_heure_fin() {
        return id_heure_fin;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public void setId_heure_debut(String id_heure_debut) {
        this.id_heure_debut = id_heure_debut;
    }

    public void setId_heure_fin(String id_heure_fin) {
        this.id_heure_fin = id_heure_fin;
    }
}