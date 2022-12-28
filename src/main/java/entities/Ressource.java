package entities;

public class Ressource {
    private int id_ressource;
    private String code_ressource;
    private String nom_ressource;

    public Ressource(int id_ressource, String code_ressource, String nom_ressource) {
        this.id_ressource = id_ressource;
        this.code_ressource = code_ressource;
        this.nom_ressource = nom_ressource;
    }

    public Ressource(){}
    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public String getCode_ressource() {
        return code_ressource;
    }

    public void setCode_ressource(String code_ressource) {
        this.code_ressource = code_ressource;
    }

    public String getNom_ressource() {
        return nom_ressource;
    }

    public void setNom_ressource(String nom_ressource) {
        this.nom_ressource = nom_ressource;
    }

    @Override
    public String toString() {
        return "Ressource{" +
                "id_ressource=" + id_ressource +
                ", code_ressource='" + code_ressource + '\'' +
                ", nom_ressource='" + nom_ressource + '\'' +
                '}';
    }
    public boolean equals(Ressource obj) {
        return this.getId_ressource() == obj.getId_ressource() && this.getCode_ressource() == obj.getCode_ressource() && this.getNom_ressource() == obj.getNom_ressource();
    }
}
