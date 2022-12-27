package entities;

public class Type_Enseignement {
    private int id_type_enseignement;
    private String libelle_type_enseignement;

    public Type_Enseignement(int id_type_enseignement, String libelle_type_enseignement) {
        this.id_type_enseignement = id_type_enseignement;
        this.libelle_type_enseignement = libelle_type_enseignement;
    }

    public Type_Enseignement(){}

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

    @Override
    public String toString() {
        return "Type_Enseignement{" +
                "id_type_enseignement=" + id_type_enseignement +
                ", libelle_type_enseignement='" + libelle_type_enseignement + '\'' +
                '}';
    }

    public boolean equals(Type_Enseignement obj) {
        return this.getId_type_enseignement() == obj.getId_type_enseignement() && this.getLibelle_type_enseignement() == obj.getLibelle_type_enseignement();
    }
}
