package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.MoyenDeContactDao;
import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoyenDeContactDaoImpl implements MoyenDeContactDao{

    @Override
    // Declaration d'une fonction renvoyant la liste des moyens de contact
    public List<MoyenDeContact> listMoyenDeContact(){

        // Declaration de la commande SQL
        String query = "SELECT * FROM moyen_de_contact ORDER BY moyen_de_contact_id";
        // Creation d'une liste
        List<MoyenDeContact> listOfMoyenDeContact = new ArrayList<>();
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
    // Declaration d'une fonction permettant d'ajouter un moyen de contact
    public MoyenDeContact addMoyenDeContact(MoyenDeContact moyenDeContact) {
        // Declaration de la commande SQL
        String query = "INSERT INTO moyen_de_contact(nom, `precision`, `url_precision`, description, url_image) VALUES(?, ?, ?, ?, ?)";
        // Connexion à la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Definition des parametres de la commande SQL
            statement.setString(1, moyenDeContact.getNom());
            statement.setString(2, moyenDeContact.getPrecision());
            statement.setString(3, moyenDeContact.getUrlPrecision());
            statement.setString(4, moyenDeContact.getDescription());
            statement.setString(5, moyenDeContact.getUrlImage());
            statement.executeUpdate();

            // Definition de l'id
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
    // Declaration d'une fonction permettant de supprimer le moyen de contact
    public void deleteMoyenDeContact(Integer id) {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            // Execution de la commande SQL
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
    // Declaration d'une fonction permettant de modifier un moyen de contact
    public void modifierMoyenDeContact(MoyenDeContact moyenDeContact) {

        // Declaration de la commande SQL
        String query = "UPDATE moyen_de_contact SET nom = ?, `precision` = ?, `url_precision` = ?, description = ?, url_image = ? where moyen_de_contact_id = ?;";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Definition des parametres de la commande SQL
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
    // Declaration d'une fonction permettant d'obtenir un moyen de contact
    public MoyenDeContact getMoyenDeContact(Integer id) {
        // Declaration de la commande SQL
        String query = "SELECT * FROM moyen_de_contact WHERE moyen_de_contact_id=?";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Creation d'un objet MoyenDeContact avec les parametres de la reponse
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
