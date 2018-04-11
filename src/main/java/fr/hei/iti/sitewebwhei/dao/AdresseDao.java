package fr.hei.iti.sitewebwhei.dao;

import fr.hei.iti.sitewebwhei.entities.Adresse;

import java.util.List;

public interface AdresseDao {

    // Declaration des fonctions de Adresse

    public List<Adresse> listAdresse();
    public Adresse addAdresse(Adresse adresse);
    public void deleteAdresse(Integer id);
    public void modifierAdresse(Adresse adresse);
    public Adresse getAdresse(Integer id);
}