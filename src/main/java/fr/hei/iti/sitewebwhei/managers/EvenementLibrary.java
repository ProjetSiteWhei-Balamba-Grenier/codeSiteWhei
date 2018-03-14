/* package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.EvenementDao;
import fr.hei.iti.sitewebwhei.dao.impl.EvenementDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Evenement;

import java.util.List;

public class EvenementLibrary {

    private EvenementDao evenementDao = new EvenementDaoImpl();

    private static class EvenementLibraryHolder {
        private final static EvenementLibrary instance = new EvenementLibrary();
    }

    public static EvenementLibrary getInstance(){return EvenementLibraryHolder.instance;}

    private EvenementLibrary(){}

    public List<Evenement> listEvenement() {return evenementDao.listEvenement();}
}
*/