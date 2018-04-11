package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.EtudiantDao;
import fr.hei.iti.sitewebwhei.entities.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoImpl implements EtudiantDao {

    @Override
    // Declaration d'une fonction renvoyant la liste des etudiants
    public List<Etudiant> listEtudiant(){

        // Declaration de la commande SQL
        String query = "SELECT * FROM etudiant ORDER BY nom";
        // Creation d'une liste
        List<Etudiant> listOfEtudiant = new ArrayList<>();
        try (
                // Connexion a la base de donnees
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                // Execution de la commande SQL
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            // Parcours du resultat
            while (resultSet.next()) {
                // Ajout de chaque resultat dans la liste precedement creee
                listOfEtudiant.add(
                        new Etudiant(
                                resultSet.getInt("etudiant_id"),
                                resultSet.getString("prenom"),
                                resultSet.getString("nom"),
                                resultSet.getString("telephone"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfEtudiant;

    }

    @Override
    // Declaration d'une fonction permettant d'ajouter une etudiant
    public Etudiant addEtudiant(Etudiant etudiant) {
        // Declaration de la commande SQL
        String query = "INSERT INTO etudiant(prenom, nom, telephone) VALUES(?, ?, ?)";
        // Connexion à la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Definition des parametres de la commande SQL
            statement.setString(1, etudiant.getPrenom());
            statement.setString(2, etudiant.getNom());
            statement.setString(3, etudiant.getTelephone());
            statement.executeUpdate();

            // Definition de l'id
            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    etudiant.setId(generatedId);
                    return etudiant;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    // Declaration d'une fonction permettant de supprimer l'etudiant
    public void deleteEtudiant(Integer id) {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            // Execution de la commande SQL
            try (PreparedStatement statement = connection.prepareStatement("delete from etudiant where etudiant_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    // Declaration d'une fonction permettant de modifier un etudiant
    public void modifierEtudiant(Etudiant etudiant) {

        // Declaration de la commande SQL
        String query = "UPDATE etudiant SET prenom = ?, nom = ?, telephone = ? where etudiant_id = ?;";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Definition des parametres de la commande SQL
            statement.setString(1, etudiant.getPrenom());
            statement.setString(2, etudiant.getNom());
            statement.setString(3, etudiant.getTelephone());
            statement.setInt(4, etudiant.getId());
            statement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // Declaration d'une fonction permettant d'obtenir un etudiant
    public Etudiant getEtudiant(Integer id) {
        // Declaration de la commande SQL
        String query = "SELECT * FROM etudiant WHERE etudiant_id=?";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Creation d'un objet Etudiant avec les parametres de la reponse
                    return new Etudiant(
                            resultSet.getInt("etudiant_id"),
                            resultSet.getString("prenom"),
                            resultSet.getString("nom"),
                            resultSet.getString("telephone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
