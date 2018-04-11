package fr.hei.iti.sitewebwhei.dao;

import fr.hei.iti.sitewebwhei.entities.Etudiant;

import java.util.List;

public interface EtudiantDao {

    // Declaration des fonctions de Etudiant

    public List<Etudiant> listEtudiant();
    public Etudiant addEtudiant(Etudiant etudiant);
    public void deleteEtudiant(Integer id);
    public void modifierEtudiant(Etudiant etudiant);
    public Etudiant getEtudiant(Integer id);
}
