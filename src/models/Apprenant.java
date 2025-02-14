package models;

public class Apprenant {
    private int id;
    private String promotion;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int nombreAbsences;
    private boolean delegue;

    public Apprenant(int id, String promotion, String nom, String prenom, String email, String telephone,
            int nombreAbsences, boolean delegue) {
        this.id = id;
        this.promotion = promotion;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.nombreAbsences = nombreAbsences;
        this.delegue = delegue;
    }

    public int getId() {
        return id;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getNombreAbsences() {
        return nombreAbsences;
    }

    public boolean isDelegue() {
        return delegue;
    }

    public void setNombreAbsences(int nombreAbsences) {
        this.nombreAbsences = nombreAbsences;
    }

    @Override
    public String toString() {
        return id + " - " + nom + " " + prenom + " (" + promotion + ") - Absences: " + nombreAbsences;
    }

}
