package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.EtudiantDao;
import fr.hei.iti.sitewebwhei.entities.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoImpl implements EtudiantDao {

    public List<Etudiant> listEtudiant(){

        String query = "SELECT * FROM etudiant ORDER BY nom";
        List<Etudiant> listOfEtudiant = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
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
    public Etudiant addEtudiant(Etudiant etudiant) {
        String query = "INSERT INTO etudiant(prenom, nom, telephone) VALUES(?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, etudiant.getPrenom());
            statement.setString(2, etudiant.getNom());
            statement.setString(3, etudiant.getTelephone());
            statement.executeUpdate();

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
    public void deleteEtudiant(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
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
    public void modifierEtudiant(Etudiant etudiant) {

        String query = "UPDATE etudiant SET prenom = ?, nom = ?, telephone = ? where etudiant_id = ?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
    public Etudiant getEtudiant(Integer id) {
        String query = "SELECT * FROM etudiant WHERE etudiant_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
