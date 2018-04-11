package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.MembreDao;
import fr.hei.iti.sitewebwhei.dao.impl.MembreDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Membre;

import java.util.List;

public class MembreLibrary {

    // Creation d'un objet MembreDao
    private MembreDao membreDao = new MembreDaoImpl();

    private static class MembreLibraryHolder {
        private final static MembreLibrary instance = new MembreLibrary();
    }

    // Creation d'une fonction retournant une instance de MembreLibrary
    public static MembreLibrary getInstance(){return MembreLibraryHolder.instance;}

    // Declaration du constructeur de MembreLibrary
    private MembreLibrary(){}

    public List<Membre> listMembre() {return membreDao.listMembre();}

    public void deleteMembre(Integer id) {membreDao.deleteMembre(id);}

    public void modifierMembre(Membre membre) {

        // Verification des informations saisies
        if (membre == null) {
            throw new IllegalArgumentException("Le membre n'existe pas.");
        }

        if (membre.getDescription() == null || "".equals(membre.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (membre.getNom() == null || "".equals(membre.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (membre.getPoste() == null || "".equals(membre.getPoste())) {
            throw new IllegalArgumentException("Le poste ne devrait pas être vide.");
        }

        if (membre.getPrenom() == null || "".equals(membre.getPrenom())) {
            throw new IllegalArgumentException("Le prénom ne devrait pas être vide.");
        }

        if (membre.getUrlImage() == null || "".equals(membre.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        membreDao.modifierMembre(membre);
    }

    public Membre addMembre(Membre membre) {

        // Verification des informations saisies
        if (membre == null) {
            throw new IllegalArgumentException("Le membre n'existe pas.");
        }

        if (membre.getDescription() == null || "".equals(membre.getDescription())) {
            throw new IllegalArgumentException("La description ne devrait pas être vide.");
        }

        if (membre.getNom() == null || "".equals(membre.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        if (membre.getPoste() == null || "".equals(membre.getPoste())) {
            throw new IllegalArgumentException("Le poste ne devrait pas être vide.");
        }

        if (membre.getPrenom() == null || "".equals(membre.getPrenom())) {
            throw new IllegalArgumentException("Le prénom ne devrait pas être vide.");
        }

        if (membre.getUrlImage() == null || "".equals(membre.getUrlImage())) {
            throw new IllegalArgumentException("L'URL image ne devrait pas être vide.");
        }

        return membreDao.addMembre(membre);
    }

    public Membre getMembre(Integer id) {return membreDao.getMembre(id);}

}
