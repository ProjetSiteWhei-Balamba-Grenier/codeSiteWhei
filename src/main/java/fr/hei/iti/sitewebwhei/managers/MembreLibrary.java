package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.MembreDao;
import fr.hei.iti.sitewebwhei.dao.impl.MembreDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Membre;

import java.util.List;

public class MembreLibrary {

    private MembreDao membreDao = new MembreDaoImpl();

    private static class MembreLibraryHolder {
        private final static MembreLibrary instance = new MembreLibrary();
    }

    public static MembreLibrary getInstance(){return MembreLibraryHolder.instance;}

    private MembreLibrary(){}

    public List<Membre> listMembre() {return membreDao.listMembre();}

    public void deleteMembre(Integer id) {membreDao.deleteMembre(id);}

    public void modifierMembre(Membre membre) {membreDao.modifierMembre(membre);}

    public Membre addMembre(Membre membre) {return membreDao.addMembre(membre);}

    public Membre getMembre(Integer id) {return membreDao.getMembre(id);}

}
