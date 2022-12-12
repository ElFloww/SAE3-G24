package dao;

public class Type_EnseignementDAO {
    private int id_type_enseignement;
    private String libelle_type_enseignement;

    public Type_EnseignementDAO(int id_type_enseignement, String libelle_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
        this.libelle_type_enseignement = libelle_type_enseignement;
    }

    public int getId_type_enseignement() {
        return id_type_enseignement;
    }

    public String getLibelle_type_enseignement() {
        return libelle_type_enseignement;
    }

    public void setId_type_enseignement(int id_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
    }

    public void setLibelle_type_enseignement(String libelle_type_enseignement) {
        this.libelle_type_enseignement = libelle_type_enseignement;
    }
}
