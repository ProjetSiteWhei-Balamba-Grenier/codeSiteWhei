package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.AdresseDao;
import fr.hei.iti.sitewebwhei.dao.impl.AdresseDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Adresse;

import java.util.List;

public class AdresseLibrary {

    // Creation d'un objet AdresseDao
    private AdresseDao adresseDao = new AdresseDaoImpl();

    private static class AdresseLibraryHolder {
        private final static AdresseLibrary instance = new AdresseLibrary();
    }

    // Creation d'une fonction retournant une instance de AdresseLibrary
    public static AdresseLibrary getInstance(){return AdresseLibraryHolder.instance;}

    // Declaration du constructeur de AdresseLibrary
    private AdresseLibrary(){}

    public List<Adresse> listAdresse() {return adresseDao.listAdresse();}

    public void deleteAdresse(Integer id) {adresseDao.deleteAdresse(id);}

    public void modifierAdresse(Adresse adresse) {

        // Verification des informations saisies
        if (adresse == null) {
            throw new IllegalArgumentException("L'adresse n'existe pas.");
        }
        if (adresse.getAdresse() == null || "".equals(adresse.getAdresse())) {
            throw new IllegalArgumentException("L'adresse ne devrait pas être vide.");
        }
        if (adresse.getDescription() == null || "".equals(adresse.getDescription())) {
            throw new IllegalArgumentException("La dersciption ne devrait pas être vide.");
        }

        if (adresse.getHoraires() == null || "".equals(adresse.getHoraires())) {
            throw new IllegalArgumentException("L'horraire ne devrait pas être vide.");
        }

        if (adresse.getNom() == null || "".equals(adresse.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (adresse.getType() == null || "".equals(adresse.getType())) {
            throw new IllegalArgumentException("Le type ne devrait pas être vide.");
        }

        if (adresse.getUrlImage() == null || "".equals(adresse.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        adresseDao.modifierAdresse(adresse);
    }

    public Adresse addAdresse(Adresse adresse) {

        // Verification des informations saisies
        if (adresse == null) {
            throw new IllegalArgumentException("L'adresse n'existe pas.");
        }
        if (adresse.getAdresse() == null || "".equals(adresse.getAdresse())) {
            throw new IllegalArgumentException("L'adresse ne devrait pas être vide.");
        }
        if (adresse.getDescription() == null || "".equals(adresse.getDescription())) {
            throw new IllegalArgumentException("La dersciption ne devrait pas être vide.");
        }

        if (adresse.getHoraires() == null || "".equals(adresse.getHoraires())) {
            throw new IllegalArgumentException("L'horraire ne devrait pas être vide.");
        }

        if (adresse.getNom() == null || "".equals(adresse.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (adresse.getType() == null || "".equals(adresse.getType())) {
            throw new IllegalArgumentException("Le type ne devrait pas être vide.");
        }

        if (adresse.getUrlImage() == null || "".equals(adresse.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        return adresseDao.addAdresse(adresse);
    }

    public Adresse getAdresse(Integer id) {return adresseDao.getAdresse(id);}
}