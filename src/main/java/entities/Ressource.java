package entities;

public class Ressource {
    private int id_ressource;
    private String nom_ressource;
    private int THTD;
    private int THTP;
    private int THCM;
    private int id_regroupement;

    public Ressource(int id_ressource, String nom_ressource, int THTD, int THTP, int THCM, int id_regroupement) {
        this.id_ressource = id_ressource;
        this.nom_ressource = nom_ressource;
        this.THTD = THTD;
        this.THTP = THTP;
        this.THCM = THCM;
        this.id_regroupement = id_regroupement;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public String getNom_ressource() {
        return nom_ressource;
    }

    public int getTHTD() {
        return THTD;
    }

    public int getTHTP() {
        return THTP;
    }

    public int getTHCM() {
        return THCM;
    }

    public int getId_regroupement() {
        return id_regroupement;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public void setNom_ressource(String nom_ressource) {
        this.nom_ressource = nom_ressource;
    }

    public void setTHTD(int THTD) {
        this.THTD = THTD;
    }

    public void setTHTP(int THTP) {
        this.THTP = THTP;
    }

    public void setTHCM(int THCM) {
        this.THCM = THCM;
    }

    public void setId_regroupement(int id_regroupement) {
        this.id_regroupement = id_regroupement;
    }
}
