package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.EvenementDao;
import fr.hei.iti.sitewebwhei.dao.impl.EvenementDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Evenement;

import java.sql.SQLException;
import java.util.List;

public class EvenementLibrary {

    private EvenementDao evenementDao = new EvenementDaoImpl();

    private static class EvenementLibraryHolder {
        private final static EvenementLibrary instance = new EvenementLibrary();
    }

    public static EvenementLibrary getInstance(){return EvenementLibraryHolder.instance;}

    private EvenementLibrary(){}

    public List<Evenement> listEvenement() {return evenementDao.listEvenement();}

    public void deleteEvenement(Integer id) {evenementDao.deleteEvenement(id);}

    public void modifierEvenement(Evenement evenement) {

        if (evenement == null) {
            throw new IllegalArgumentException("L'evenement n'existe pas.");
        }
        if (evenement.getNom() == null || "".equals(evenement.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (evenement.getDescription() == null || "".equals(evenement.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (evenement.getAdresse() == null || "".equals(evenement.getAdresse())) {
            throw new IllegalArgumentException("L'adresse ne devrait pas être vide.");
        }

        if (evenement.getHoraires() == null || "".equals(evenement.getHoraires())) {
            throw new IllegalArgumentException("L'horaire ne devrait pas être vide.");
        }

        if (evenement.getUrlImage() == null || "".equals(evenement.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        evenementDao.modifierEvenement(evenement);
    }

    public Evenement addEvenement(Evenement evenement) {

        if (evenement == null) {
            throw new IllegalArgumentException("L'evenement n'existe pas.");
        }
        if (evenement.getNom() == null || "".equals(evenement.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (evenement.getDescription() == null || "".equals(evenement.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (evenement.getAdresse() == null || "".equals(evenement.getAdresse())) {
            throw new IllegalArgumentException("L'adresse ne devrait pas être vide.");
        }

        if (evenement.getHoraires() == null || "".equals(evenement.getHoraires())) {
            throw new IllegalArgumentException("L'horaire ne devrait pas être vide.");
        }

        if (evenement.getUrlImage() == null || "".equals(evenement.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        return evenementDao.addEvenement(evenement);
    }

    public Evenement getEvenement(Integer id){return evenementDao.getEvenement(id);}
}
