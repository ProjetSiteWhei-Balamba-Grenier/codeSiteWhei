package fr.hei.iti.sitewebwhei.entities;

import javax.print.DocFlavor;

public class Adresse {

    private Integer id;
    private String nom;
    private String type;
    private String adresse;
    private String horaires;
    private String description;
    private String urlImage;

    public Adresse(Integer id, String nom, String type, String adresse, String horaires, String description, String urlImage){
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.horaires = horaires;
        this.description = description;
        this.urlImage = urlImage;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
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