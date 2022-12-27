package entities;

public class Heure {
    private int id_heure;
    private String heure;

    public Heure(int id_heure, String heure) {
        this.id_heure = id_heure;
        this.heure = heure;
    }

    public int getId_Heure() {
        return id_heure;
    }

    public String getHeure() {
        return heure;
    }

    public void setId_heure(int id_heure) {
        this.id_heure = id_heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
