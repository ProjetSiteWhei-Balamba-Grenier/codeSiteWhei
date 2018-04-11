package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.MoyenDeContactDao;
import fr.hei.iti.sitewebwhei.dao.impl.MoyenDeContactDaoImpl;
import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;

import java.util.List;

public class MoyenDeContactLibrary {

    // Creation d'un objet MoyenDeContactDao
    private MoyenDeContactDao moyenDeContactDao = new MoyenDeContactDaoImpl();

    private static class MoyenDeContactLibraryHolder {
        private final static MoyenDeContactLibrary instance = new MoyenDeContactLibrary();
    }

    // Creation d'une fonction retournant une instance de MoyenDeContactLibrary
    public static MoyenDeContactLibrary getInstance(){return MoyenDeContactLibraryHolder.instance;}

    // Declaration du constructeur de MoyenDeContactLibrary
    private MoyenDeContactLibrary(){}

    public List<MoyenDeContact> listMoyenDeContact() {return moyenDeContactDao.listMoyenDeContact();}

    public void deleteMoyenDeContact(Integer id) {moyenDeContactDao.deleteMoyenDeContact(id);}

    public void modifierMoyenDeContact(MoyenDeContact moyenDeContact) {

        // Verification des informations saisies
        if (moyenDeContact == null) {
            throw new IllegalArgumentException("Le moyen de contact n'existe pas.");
        }

        if (moyenDeContact.getDescription() == null || "".equals(moyenDeContact.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (moyenDeContact.getNom() == null || "".equals(moyenDeContact.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (moyenDeContact.getPrecision() == null || "".equals(moyenDeContact.getPrecision())) {
            throw new IllegalArgumentException("La précision ne devrait pas être vide.");
        }

        if (moyenDeContact.getUrlImage() == null || "".equals(moyenDeContact.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        if (moyenDeContact.getUrlPrecision() == null || "".equals(moyenDeContact.getUrlPrecision())) {
            throw new IllegalArgumentException("L'URL précision ne devrait pas être vide.");
        }

        moyenDeContactDao.modifierMoyenDeContact(moyenDeContact);
    }

    public MoyenDeContact addMoyenDeContact(MoyenDeContact moyenDeContact) {

        // Verification des informations saisies
        if (moyenDeContact == null) {
            throw new IllegalArgumentException("Le moyen de contact n'existe pas.");
        }

        if (moyenDeContact.getDescription() == null || "".equals(moyenDeContact.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (moyenDeContact.getNom() == null || "".equals(moyenDeContact.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (moyenDeContact.getPrecision() == null || "".equals(moyenDeContact.getPrecision())) {
            throw new IllegalArgumentException("La précision ne devrait pas être vide.");
        }

        if (moyenDeContact.getUrlImage() == null || "".equals(moyenDeContact.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        if (moyenDeContact.getUrlPrecision() == null || "".equals(moyenDeContact.getUrlPrecision())) {
            throw new IllegalArgumentException("L'URL précision ne devrait pas être vide.");
        }

        return moyenDeContactDao.addMoyenDeContact(moyenDeContact);
    }

    public MoyenDeContact getMoyenDeContact(Integer id) {return moyenDeContactDao.getMoyenDeContact(id);}

}
