package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.AdresseDao;
import fr.hei.iti.sitewebwhei.entities.Adresse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresseDaoImpl implements AdresseDao {

    @Override
    public List<Adresse> listAdresse(){

        String query = "SELECT * FROM adresse ORDER BY adresse_id";
        List<Adresse> listOfAdresses = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfAdresses.add(
                        new Adresse(
                                resultSet.getInt("adresse_id"),
                                resultSet.getString("nom"),
                                resultSet.getString("adresse_type"),
                                resultSet.getString("adresse"),
                                resultSet.getString("horaires"),
                                resultSet.getString("description"),
                                resultSet.getString("url_image"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAdresses;
    }

    @Override
    public Adresse addAdresse(Adresse adresse) {
        String query = "INSERT INTO adresse(nom, adresse_type, adresse, horaires, description, url_image) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, adresse.getNom());
            statement.setString(2, adresse.getType());
            statement.setString(3, adresse.getAdresse());
            statement.setString(4, adresse.getHoraires());
            statement.setString(5, adresse.getDescription());
            statement.setString(6, adresse.getUrlImage());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    adresse.setId(generatedId);
                    return adresse;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAdresse(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from adresse where adresse_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifierAdresse(Adresse adresse) {

        String query = "UPDATE adresse SET nom = ?, adresse_type = ?, adresse = ?, horaires = ?, description = ?, url_image = ? where adresse_id = ?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adresse.getNom());
            statement.setString(2, adresse.getType());
            statement.setString(3, adresse.getAdresse());
            statement.setString(4, adresse.getHoraires());
            statement.setString(5, adresse.getDescription());
            statement.setString(6, adresse.getUrlImage());
            statement.setInt(7, adresse.getId());
            statement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Adresse getAdresse(Integer id) {
        String query = "SELECT * FROM adresse WHERE adresse_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Adresse(
                            resultSet.getInt("adresse_id"),
                            resultSet.getString("nom"),
                            resultSet.getString("adresse_type"),
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