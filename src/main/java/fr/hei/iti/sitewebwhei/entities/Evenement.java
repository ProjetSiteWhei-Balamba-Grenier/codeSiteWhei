package fr.hei.iti.sitewebwhei.entities;

public class Evenement {

    private Integer id;
    private String nom;
    private String adresse;
    private String horaire;
    private String mail;
    private String description;
    private String urlImage;

    public Evenement (Integer id, String nom, String adresse, String horaire, String description, String url_image) {

        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.horaire = horaire;
        this.description = description;
        this.urlImage = url_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHoraires() {
        return horaire;
    }

    public void setHoraires(String horaires) {
        this.horaire = horaires;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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