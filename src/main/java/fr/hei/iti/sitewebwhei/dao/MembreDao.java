package fr.hei.iti.sitewebwhei.dao;

import fr.hei.iti.sitewebwhei.entities.Membre;

import java.util.List;

public interface MembreDao {

    public List<Membre> listMembre();
    public Membre addMembre(Membre membre);
    public void modifierMembre(Membre membre);
    public void deleteMembre(Integer id);
    public Membre getMembre(Integer id);
}
