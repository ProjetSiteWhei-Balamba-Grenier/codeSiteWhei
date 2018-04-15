package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.EvenementDao;
import fr.hei.iti.sitewebwhei.entities.Evenement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementDaoImpl implements EvenementDao {

    @Override
    public List<Evenement> listEvenement() {

        String query = "SELECT * FROM evenement ORDER BY evenement_id";
        List<Evenement> listOfEvenement = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfEvenement.add(
                        new Evenement(
                                resultSet.getInt("evenement_id"),
                                resultSet.getString("nom"),
                                resultSet.getString("adresse"),
                                resultSet.getString("horaires"),
                                resultSet.getString("description"),
                                resultSet.getString("url_image"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfEvenement;
    }

    @Override
    public Evenement addEvenement(Evenement evenement) {
        String query = "INSERT INTO evenement(nom, adresse, horaires, description, url_image) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, evenement.getNom());
            statement.setString(2, evenement.getAdresse());
            statement.setString(3, evenement.getHoraires());
            statement.setString(4, evenement.getDescription());
            statement.setString(5, evenement.getUrlImage());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    evenement.setId(generatedId);
                    return evenement;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void deleteEvenement(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from evenement where evenement_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifierEvenement(Evenement evenement) {

        String query = "UPDATE evenement SET nom = ?, adresse = ?, horaires = ?, description = ?, url_image = ? where evenement_id = ?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, evenement.getNom());
            statement.setString(2, evenement.getAdresse());
            statement.setString(3, evenement.getHoraires());
            statement.setString(4, evenement.getDescription());
            statement.setString(5, evenement.getUrlImage());
            statement.setInt(6, evenement.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Evenement getEvenement(Integer id) {
        String query = "SELECT * FROM evenement WHERE evenement_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Evenement(
                            resultSet.getInt("evenement_id"),
                            resultSet.getString("nom"),
                            resultSet.getString("adresse"),
                            resultSet.getString("horaires"),
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

