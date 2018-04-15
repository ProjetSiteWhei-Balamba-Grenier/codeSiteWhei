package fr.hei.iti.sitewebwhei.dao;

import fr.hei.iti.sitewebwhei.entities.Evenement;

import java.sql.SQLException;
import java.util.List;

public interface EvenementDao {

    public List<Evenement> listEvenement();
    public Evenement addEvenement(Evenement evenement);
    public void deleteEvenement(Integer id);
    public void modifierEvenement(Evenement evenement);
    public Evenement getEvenement(Integer id);
}

