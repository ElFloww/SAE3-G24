package entities;

import java.sql.Time;

public class Heure {
    private int id_heure;
    private Time heure;

    public Heure(int id_heure, Time heure) {
        this.id_heure = id_heure;
        this.heure = heure;
    }
    public Heure(){}

    public int getId_Heure() {
        return id_heure;
    }

    public Time getHeure() {
        return heure;
    }

    public void setId_heure(int id_heure) {
        this.id_heure = id_heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Heure{" +
                "id_heure=" + id_heure +
                ", heure='" + heure + '\'' +
                '}';
    }
    public boolean equals(Heure obj) {
        return this.getId_Heure() == obj.getId_Heure() && this.getHeure() == obj.getHeure();
    }
}
