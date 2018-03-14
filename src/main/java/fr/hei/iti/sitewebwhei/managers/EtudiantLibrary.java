package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.EtudiantDao;
import fr.hei.iti.sitewebwhei.dao.impl.EtudiantDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Etudiant;

import java.util.List;

public class EtudiantLibrary {

    private EtudiantDao etudiantDao = new EtudiantDaoImpl();

    private static class EtudiantLibraryHolder {
        private final static EtudiantLibrary instance = new EtudiantLibrary();
    }

    public static EtudiantLibrary getInstance(){return EtudiantLibraryHolder.instance;}

    private EtudiantLibrary(){}

    public List<Etudiant> listEtudiant() {return etudiantDao.listEtudiant();}

    public Etudiant addEtudiant(Etudiant etudiant) {return etudiantDao.addEtudiant(etudiant);}

    public void deleteEtudiant(Integer id) {etudiantDao.deleteEtudiant(id);}

    public void modifierEtudiant(Etudiant etudiant) {etudiantDao.modifierEtudiant(etudiant);}

    public Etudiant getEtudiant(Integer id) {return etudiantDao.getEtudiant(id);}
}
