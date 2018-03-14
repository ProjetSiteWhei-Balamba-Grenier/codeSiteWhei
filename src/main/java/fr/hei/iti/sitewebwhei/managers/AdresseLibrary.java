package fr.hei.iti.sitewebwhei.managers;

import fr.hei.iti.sitewebwhei.dao.AdresseDao;
import fr.hei.iti.sitewebwhei.dao.impl.AdresseDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Adresse;

import java.util.List;

public class AdresseLibrary {

    private AdresseDao adresseDao = new AdresseDaoImpl();

    private static class AdresseLibraryHolder {
        private final static AdresseLibrary instance = new AdresseLibrary();
    }

    public static AdresseLibrary getInstance(){return AdresseLibraryHolder.instance;}

    private AdresseLibrary(){}

    public List<Adresse> listAdresse() {return adresseDao.listAdresse();}

    public void deleteAdresse(Integer id) {adresseDao.deleteAdresse(id);}

    public void modifierAdresse(Adresse adresse) {adresseDao.modifierAdresse(adresse);}

    public Adresse addAdresse(Adresse adresse) {return adresseDao.addAdresse(adresse);}

    public Adresse getAdresse(Integer id) {return adresseDao.getAdresse(id);}
}