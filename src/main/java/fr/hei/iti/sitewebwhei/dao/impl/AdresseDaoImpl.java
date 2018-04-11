package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.AdresseDao;
import fr.hei.iti.sitewebwhei.entities.Adresse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresseDaoImpl implements AdresseDao {

    @Override
    // Declaration d'une fonction renvoyant la liste des adresses
    public List<Adresse> listAdresse(){

        // Declaration de la commande SQL
        String query = "SELECT * FROM adresse ORDER BY adresse_id";
        // Creation d'une liste
        List<Adresse> listOfAdresses = new ArrayList<>();
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
    // Declaration d'une fonction permettant d'ajouter une adresse
    public Adresse addAdresse(Adresse adresse) {
        // Declaration de la commande SQL
        String query = "INSERT INTO adresse(nom, adresse_type, adresse, horaires, description, url_image) VALUES(?, ?, ?, ?, ?, ?)";
        // Connexion à la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Definition des parametres de la commande SQL
            statement.setString(1, adresse.getNom());
            statement.setString(2, adresse.getType());
            statement.setString(3, adresse.getAdresse());
            statement.setString(4, adresse.getHoraires());
            statement.setString(5, adresse.getDescription());
            statement.setString(6, adresse.getUrlImage());
            statement.executeUpdate();

            // Definition de l'id
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
    // Declaration d'une fonction permettant de supprimer l'adresse
    public void deleteAdresse(Integer id) {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            // Execution de la commande SQL
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
    // Declaration d'une fonction permettant de modifier une adresse
    public void modifierAdresse(Adresse adresse) {

        // Declaration de la commande SQL
        String query = "UPDATE adresse SET nom = ?, adresse_type = ?, adresse = ?, horaires = ?, description = ?, url_image = ? where adresse_id = ?;";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Definition des parametres de la commande SQL
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
    // Declaration d'une fonction permettant d'obtenir une adresse
    public Adresse getAdresse(Integer id) {
        // Declaration de la commande SQL
        String query = "SELECT * FROM adresse WHERE adresse_id=?";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Creation d'un objet Adresse avec les parametres de la reponse
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