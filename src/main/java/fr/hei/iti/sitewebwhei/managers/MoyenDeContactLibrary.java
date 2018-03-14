package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.MoyenDeContactDao;
import fr.hei.iti.sitewebwhei.dao.impl.MoyenDeContactDaoImpl;
import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;

import java.util.List;

public class MoyenDeContactLibrary {

    private MoyenDeContactDao moyenDeContactDao = new MoyenDeContactDaoImpl();

    private static class MoyenDeContactLibraryHolder {
        private final static MoyenDeContactLibrary instance = new MoyenDeContactLibrary();
    }

    public static MoyenDeContactLibrary getInstance(){return MoyenDeContactLibraryHolder.instance;}

    private MoyenDeContactLibrary(){}

    public List<MoyenDeContact> listMoyenDeContact() {return moyenDeContactDao.listMoyenDeContact();}

    public void deleteMoyenDeContact(Integer id) {moyenDeContactDao.deleteMoyenDeContact(id);}

    public void modifierMoyenDeContact(MoyenDeContact moyenDeContact) {moyenDeContactDao.modifierMoyenDeContact(moyenDeContact);}

    public MoyenDeContact addMoyenDeContact(MoyenDeContact moyenDeContact) {return moyenDeContactDao.addMoyenDeContact(moyenDeContact);}

    public MoyenDeContact getMoyenDeContact(Integer id) {return moyenDeContactDao.getMoyenDeContact(id);}

}
