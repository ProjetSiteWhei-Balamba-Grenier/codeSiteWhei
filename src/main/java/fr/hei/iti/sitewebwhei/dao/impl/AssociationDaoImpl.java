package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.AssociationDao;
import fr.hei.iti.sitewebwhei.entities.Association;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssociationDaoImpl implements AssociationDao {

    @Override
    public List<Association> listAssociation() {

        String query = "SELECT * FROM association ORDER BY association_id";
        List<Association> listOfAssociation = new ArrayList<>();

        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfAssociation.add(
                        new Association(
                                resultSet.getInt("association_id"),
                                resultSet.getString("nom"),
                                resultSet.getString("description"),
                                resultSet.getString("nomPresident"),
                                resultSet.getString("nomFB"),
                                resultSet.getString("mail"),
                                resultSet.getString("pole"),
                                resultSet.getString("url_image"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAssociation;
    }

    @Override
    public Association addAssociation(Association association) {
        String query = "INSERT INTO association(nom, description, nomPresident, nomFB, mail, pole, url_image) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, association.getNom());
            statement.setString(2, association.getDescription());
            statement.setString(3, association.getNomPresident());
            statement.setString(4, association.getNomFb());
            statement.setString(5, association.getMail());
            statement.setString(6, association.getPole());
            statement.setString(7, association.getUrlImage());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    association.setId(generatedId);
                    return association;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAssociation(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from association where association_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifierAssociation(Association association) {

        String query = "UPDATE association SET nom = ?, nomFB = ?, nomPresident= ?, mail = ?, description = ?, pole = ?, url_image = ? where association_id = ?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, association.getNom());
            statement.setString(2, association.getNomFb());
            statement.setString(3, association.getNomPresident());
            statement.setString(4, association.getMail());
            statement.setString(5,association.getDescription());
            statement.setString(6, association.getPole());
            statement.setString(7, association.getUrlImage());
            statement.setInt(8,association.getId());
            statement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Association getAssociation(Integer id) {
        String query = "SELECT * FROM association WHERE association_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Association(
                            resultSet.getInt("association_id"),
                            resultSet.getString("nom"),
                            resultSet.getString("description"),
                            resultSet.getString("nomPresident"),
                            resultSet.getString("nomFB"),
                            resultSet.getString("mail"),
                            resultSet.getString("pole"),
                            resultSet.getString("url_image"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



