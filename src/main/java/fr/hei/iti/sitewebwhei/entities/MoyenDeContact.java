package fr.hei.iti.sitewebwhei.entities;

public class MoyenDeContact {

    private Integer id;
    private String nom;
    private String precision;
    private String description;
    private String urlImage;

    public MoyenDeContact(Integer id, String nom, String precision, String description, String urlImage){
        this.id = id;
        this.nom = nom;
        this.precision = precision;
        this.description = description;
        this.urlImage = urlImage;
    }

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
}
