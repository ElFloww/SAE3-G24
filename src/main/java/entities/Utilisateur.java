package entities;

public class Utilisateur {

    private int id_utilisateur;
    private String nom;
    private String prenom;
    private String statut;
    private String mdp_crypte;
    private int id_regroupement;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id_utilisateur=" + id_utilisateur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", statut='" + statut + '\'' +
                ", mdp_crypte='" + mdp_crypte + '\'' +
                ", id_regroupement=" + id_regroupement +
                '}';
    }

    public Utilisateur() {
    }

    public Utilisateur(int id_utilisateur, String nom, String prenom, String statut, String mdp_crypte, int id_regroupement) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.statut = statut;
        this.mdp_crypte = mdp_crypte;
        this.id_regroupement = id_regroupement;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getStatut() {
        return statut;
    }

    public String getMdp_crypte() {
        return mdp_crypte;
    }

    public int getId_regroupement() {
        return id_regroupement;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setMdp_crypte(String mdp_crypte) {
        this.mdp_crypte = mdp_crypte;
    }

    public void setId_regroupement(int id_regroupement) {
        this.id_regroupement = id_regroupement;
    }

    public boolean equals(Utilisateur obj) {
        return this.getId_utilisateur() == obj.getId_utilisateur() & this.getNom() == obj.getNom() && this.getPrenom() == obj.getPrenom() && this.getStatut() == obj.getStatut() && this.getMdp_crypte() == obj.getMdp_crypte() && this.getId_regroupement() == obj.getId_regroupement();
    }
}
