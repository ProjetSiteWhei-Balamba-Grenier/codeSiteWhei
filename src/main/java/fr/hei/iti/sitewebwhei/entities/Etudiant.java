package fr.hei.iti.sitewebwhei.entities;

public class Etudiant {

    // Declaration des parametres de Etudiant

    private Integer id;
    private String prenom;
    private String nom;
    private String telephone;

    // Declaration du constructeur de Etudiant

    public Etudiant(Integer id, String prenom, String nom, String telephone){
        this.id = id;
        this.prenom  = prenom;
        this.nom = nom;
        this.telephone = telephone;
    }

    // Declaration des getters et setters de Etudiant

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
