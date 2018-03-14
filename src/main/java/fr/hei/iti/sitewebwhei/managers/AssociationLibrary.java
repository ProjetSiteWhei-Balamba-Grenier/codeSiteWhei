/* package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.AssociationDao;
import fr.hei.iti.sitewebwhei.dao.impl.AssocitionDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Association;

import java.util.List;

public class AssociationLibrary {

    private AssociationDao associationDao = new AssocitionDaoImpl();

    private static class AssociationLibraryHolder {
        private final static AssociationLibrary instance = new AssociationLibrary();
    }

    public static AssociationLibrary getInstance(){return AssociationLibraryHolder.instance;}

    private AssociationLibrary(){}

    public List<Association> listAssociation() {return associationDao.listAssociation();}
}
*/