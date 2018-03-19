package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.MoyenDeContactDao;
import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoyenDeContactDaoImpl implements MoyenDeContactDao{

    @Override
    public List<MoyenDeContact> listMoyenDeContact(){

        String query = "SELECT * FROM moyen_de_contact ORDER BY moyen_de_contact_id";
        List<MoyenDeContact> listOfMoyenDeContact = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfMoyenDeContact.add(
                        new MoyenDeContact(
                                resultSet.getInt("moyen_de_contact_id"),
                                resultSet.getString("nom"),
                                resultSet.getString("precision"),
                                resultSet.getString("url_precision"),
                                resultSet.getString("description"),
                                resultSet.getString("url_image"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfMoyenDeContact;
    }

    @Override
    public MoyenDeContact addMoyenDeContact(MoyenDeContact moyenDeContact) {
        String query = "INSERT INTO moyen_de_contact(nom, `precision`, `url_precision`, description, url_image) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, moyenDeContact.getNom());
            statement.setString(2, moyenDeContact.getPrecision());
            statement.setString(3, moyenDeContact.getUrlPrecision());
            statement.setString(4, moyenDeContact.getDescription());
            statement.setString(5, moyenDeContact.getUrlImage());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    moyenDeContact.setId(generatedId);
                    return moyenDeContact;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteMoyenDeContact(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from moyen_de_contact where moyen_de_contact_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifierMoyenDeContact(MoyenDeContact moyenDeContact) {

        String query = "UPDATE moyen_de_contact SET nom = ?, `precision` = ?, `url_precision` = ?, description = ?, url_image = ? where moyen_de_contact_id = ?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, moyenDeContact.getNom());
            statement.setString(2, moyenDeContact.getPrecision());
            statement.setString(3, moyenDeContact.getUrlPrecision());
            statement.setString(4, moyenDeContact.getDescription());
            statement.setString(5, moyenDeContact.getUrlImage());
            statement.setInt(6, moyenDeContact.getId());
            statement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MoyenDeContact getMoyenDeContact(Integer id) {
        String query = "SELECT * FROM moyen_de_contact WHERE moyen_de_contact_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new MoyenDeContact(
                            resultSet.getInt("moyen_de_contact_id"),
                            resultSet.getString("nom"),
                            resultSet.getString("precision"),
                            resultSet.getString("url_precision"),
                            resultSet.getString("description"),
                            resultSet.getString("url_image"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
