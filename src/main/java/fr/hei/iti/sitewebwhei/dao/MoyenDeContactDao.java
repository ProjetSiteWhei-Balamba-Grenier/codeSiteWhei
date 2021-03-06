package fr.hei.iti.sitewebwhei.dao;

import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;

import java.util.List;

public interface MoyenDeContactDao {

    // Declaration des fonctions de MoyenDeContact

    public List<MoyenDeContact> listMoyenDeContact();
    public MoyenDeContact addMoyenDeContact(MoyenDeContact moyenDeContact);
    public void modifierMoyenDeContact(MoyenDeContact moyenDeContact);
    public void deleteMoyenDeContact(Integer id);
    public MoyenDeContact getMoyenDeContact(Integer id);
}
