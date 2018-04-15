package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.AssociationDao;
import fr.hei.iti.sitewebwhei.dao.impl.AssociationDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Association;

import java.util.List;

public class AssociationLibrary {

    private AssociationDao associationDao = new AssociationDaoImpl();

    private static class AssociationLibraryHolder {
        private final static AssociationLibrary instance = new AssociationLibrary();
    }

    public static AssociationLibrary getInstance(){return AssociationLibraryHolder.instance;}

    private AssociationLibrary(){}

    public List<Association> listAssociation() {return associationDao.listAssociation();}

    public void deleteAssociation(Integer id) {associationDao.deleteAssociation(id);}

    public void modifierAssociation(Association association) {

        if (association == null) {
            throw new IllegalArgumentException("L'association n'existe pas.");
        }
        if (association.getNom() == null || "".equals(association.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (association.getDescription() == null || "".equals(association.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (association.getNomPresident() == null || "".equals(association.getNomPresident())) {
            throw new IllegalArgumentException("Le nom du president ne devrait pas être vide.");
        }

        if (association.getNomFb() == null || "".equals(association.getNomFb())) {
            throw new IllegalArgumentException("Le nom Facebook ne devrait pas être vide.");
        }

        if (association.getMail() == null || "".equals(association.getMail())) {
            throw new IllegalArgumentException("Le mail ne devrait pas être vide.");
        }

        if (association.getPole() == null || "".equals(association.getPole())) {
            throw new IllegalArgumentException("Le pole ne devrait pas être vide.");
        }

        if (association.getUrlImage() == null || "".equals(association.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        associationDao.modifierAssociation(association);
    }

    public Association addAssociation(Association association) {

        if (association == null) {
            throw new IllegalArgumentException("L'association n'existe pas.");
        }
        if (association.getNom() == null || "".equals(association.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (association.getDescription() == null || "".equals(association.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (association.getNomPresident() == null || "".equals(association.getNomPresident())) {
            throw new IllegalArgumentException("Le nom du president ne devrait pas être vide.");
        }

        if (association.getNomFb() == null || "".equals(association.getNomFb())) {
            throw new IllegalArgumentException("Le nom Facebook ne devrait pas être vide.");
        }

        if (association.getMail() == null || "".equals(association.getMail())) {
            throw new IllegalArgumentException("Le mail ne devrait pas être vide.");
        }

        if (association.getPole() == null || "".equals(association.getPole())) {
            throw new IllegalArgumentException("Le pole ne devrait pas être vide.");
        }

        if (association.getUrlImage() == null || "".equals(association.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        return associationDao.addAssociation(association);
    }

    public Association getAssociation(Integer id) {return associationDao.getAssociation(id);}
}

