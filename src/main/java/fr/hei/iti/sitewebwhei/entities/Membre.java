package fr.hei.iti.sitewebwhei.entities;

public class Membre {

    private Integer id;
    private String prenom;
    private String nom;
    private String poste;
    private String description;
    private String urlImage;

    public Membre (Integer id, String prenom, String nom, String poste, String description, String urlImage){
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.poste = poste;
        this.description = description;
        this.urlImage = urlImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
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
