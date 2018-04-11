package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.EtudiantDao;
import fr.hei.iti.sitewebwhei.dao.impl.EtudiantDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Etudiant;

import java.util.List;

public class EtudiantLibrary {

    // Creation d'un objet EtudiantDao
    private EtudiantDao etudiantDao = new EtudiantDaoImpl();

    private static class EtudiantLibraryHolder {
        private final static EtudiantLibrary instance = new EtudiantLibrary();
    }

    // Creation d'une fonction retournant une instance de EtudiantLibrary
    public static EtudiantLibrary getInstance(){return EtudiantLibraryHolder.instance;}

    // Declaration du constructeur de EtudiantLibrary
    private EtudiantLibrary(){}

    public List<Etudiant> listEtudiant() {return etudiantDao.listEtudiant();}

    public Etudiant addEtudiant(Etudiant etudiant) {

        // Verification des informations saisies
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant n'existe pas.");
        }
        if (etudiant.getTelephone() == null || "".equals(etudiant.getTelephone())) {
            throw new IllegalArgumentException("Le téléphone ne devrait pas être vide.");
        }

        if (etudiant.getPrenom() == null || "".equals(etudiant.getPrenom())) {
            throw new IllegalArgumentException("Le prénom ne devrait pas être vide.");
        }

        if (etudiant.getNom() == null || "".equals(etudiant.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        return etudiantDao.addEtudiant(etudiant);
    }

    public void deleteEtudiant(Integer id) {etudiantDao.deleteEtudiant(id);}

    public void modifierEtudiant(Etudiant etudiant) {

        // Verification des informations saisies
        if (etudiant == null) {
            throw new IllegalArgumentException("L'etudiant n'existe pas.");
        }
        if (etudiant.getTelephone() == null || "".equals(etudiant.getTelephone())) {
            throw new IllegalArgumentException("Le téléphone ne devrait pas être vide.");
        }

        if (etudiant.getPrenom() == null || "".equals(etudiant.getPrenom())) {
            throw new IllegalArgumentException("Le prénom ne devrait pas être vide.");
        }

        if (etudiant.getNom() == null || "".equals(etudiant.getNom())) {
            throw new IllegalArgumentException("Le nom ne devrait pas être vide.");
        }

        etudiantDao.modifierEtudiant(etudiant);
    }

    public Etudiant getEtudiant(Integer id) {return etudiantDao.getEtudiant(id);}
}
