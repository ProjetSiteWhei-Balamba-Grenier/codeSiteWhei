package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.MembreDao;
import fr.hei.iti.sitewebwhei.entities.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {

    @Override
    public List<Membre> listMembre(){

        String query = "SELECT * FROM membre ORDER BY membre_id";
        List<Membre> listOfMembre = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfMembre.add(
                        new Membre(
                                resultSet.getInt("membre_id"),
                                resultSet.getString("prenom"),
                                resultSet.getString("nom"),
                                resultSet.getString("poste"),
                                resultSet.getString("description"),
                                resultSet.getString("url_image"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfMembre;
    }

    @Override
    public Membre addMembre(Membre membre) {
        String query = "INSERT INTO membre(prenom, nom, poste, description, url_image) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, membre.getPrenom());
            statement.setString(2, membre.getNom());
            statement.setString(3, membre.getPoste());
            statement.setString(4, membre.getDescription());
            statement.setString(5, membre.getUrlImage());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    membre.setId(generatedId);
                    return membre;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteMembre(Integer id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from membre where membre_id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }

    @Override
    public void modifierMembre(Membre membre) {

        String query = "UPDATE membre SET prenom = ?, nom = ?, poste = ?, description = ?, url_image = ? where membre_id = ?;";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, membre.getPrenom());
            statement.setString(2, membre.getNom());
            statement.setString(3, membre.getPoste());
            statement.setString(4, membre.getDescription());
            statement.setString(5, membre.getUrlImage());
            statement.setInt(6, membre.getId());
            statement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Membre getMembre(Integer id) {
        String query = "SELECT * FROM membre WHERE membre_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Membre(
                            resultSet.getInt("membre_id"),
                            resultSet.getString("prenom"),
                            resultSet.getString("nom"),
                            resultSet.getString("poste"),
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
