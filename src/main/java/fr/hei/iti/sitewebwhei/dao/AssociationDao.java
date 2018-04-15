package fr.hei.iti.sitewebwhei.dao;

import fr.hei.iti.sitewebwhei.entities.Association;

import java.util.List;

public interface AssociationDao {

    public List<Association> listAssociation();
    public Association addAssociation(Association association);
    public void deleteAssociation(Integer id);
    public void modifierAssociation(Association association);
    public Association getAssociation(Integer id);
}

