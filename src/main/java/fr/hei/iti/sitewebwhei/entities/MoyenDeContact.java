package fr.hei.iti.sitewebwhei.entities;

public class MoyenDeContact {

    // Declaration des paramettres de MoyenDeContact

    private Integer id;
    private String nom;
    private String precision;
    private String urlPrecision;
    private String description;
    private String urlImage;

    // Declaration du constructeur de MoyenDeContact

    public MoyenDeContact(Integer id, String nom, String precision, String urlPrecision, String description, String urlImage){
        this.id = id;
        this.nom = nom;
        this.precision = precision;
        this.urlPrecision = urlPrecision;
        this.description = description;
        this.urlImage = urlImage;
    }

    // Declaration des getters et setters de MoyenDeContact

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlPrecision() { return urlPrecision; }

    public void setUrlPrecision(String urlPrecision) { this.urlPrecision = urlPrecision;    }
}
