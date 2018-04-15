package fr.hei.iti.sitewebwhei.entities;

public class Association {

    private Integer id;
    private String nom;
    private String nomFb;
    private String nomPresident;
    private String description;
    private String mail;
    private String pole;
    private String urlImage;

    public Association (Integer id, String nom, String description, String nomPresident, String nomFb, String mail, String pole, String urlImage){
        this.id = id;
        this.nom = nom;
        this.nomFb = nomFb;
        this.nomPresident = nomPresident;
        this. mail=mail;
        this.description = description;
        this.urlImage = urlImage;
        this.pole = pole;
    }

    public String getPole() {
        return pole;
    }

    public void setPole(String pole) {
        this.pole = pole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {return nom;}

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomPresident() {
        return nomPresident;
    }

    public void setNomPresident(String nomPresident) {
        this.nomPresident = nomPresident;
    }

    public String getNomFb() {
        return nomFb;
    }

    public void setNomFb(String nomFb) {
        this.nomFb = nomFb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
