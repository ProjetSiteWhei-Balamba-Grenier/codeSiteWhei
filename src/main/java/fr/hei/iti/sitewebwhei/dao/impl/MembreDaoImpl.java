package fr.hei.iti.sitewebwhei.dao.impl;

import fr.hei.iti.sitewebwhei.dao.MembreDao;
import fr.hei.iti.sitewebwhei.entities.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {

    @Override
    // Declaration d'une fonction renvoyant la liste des membres
    public List<Membre> listMembre(){

        // Declaration de la commande SQL
        String query = "SELECT * FROM membre ORDER BY membre_id";
        // Creation d'une liste
        List<Membre> listOfMembre = new ArrayList<>();
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
    // Declaration d'une fonction permettant d'ajouter une membre
    public Membre addMembre(Membre membre) {
        // Declaration de la commande SQL
        String query = "INSERT INTO membre(prenom, nom, poste, description, url_image) VALUES(?, ?, ?, ?, ?)";
        // Connexion à la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Definition des parametres de la commande SQL
            statement.setString(1, membre.getPrenom());
            statement.setString(2, membre.getNom());
            statement.setString(3, membre.getPoste());
            statement.setString(4, membre.getDescription());
            statement.setString(5, membre.getUrlImage());
            statement.executeUpdate();

            // Definition de l'id
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
    // Declaration d'une fonction permettant de supprimer le membre
    public void deleteMembre(Integer id) {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            // Execution de la commande SQL
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
    // Declaration d'une fonction permettant de modifier un membre
    public void modifierMembre(Membre membre) {

        // Declaration de la commande SQL
        String query = "UPDATE membre SET prenom = ?, nom = ?, poste = ?, description = ?, url_image = ? where membre_id = ?;";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Definition des parametres de la commande SQL
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
    // Declaration d'une fonction permettant d'obtenir un membre
    public Membre getMembre(Integer id) {
        // Declaration de la commande SQL
        String query = "SELECT * FROM membre WHERE membre_id=?";
        // Connexion a la base de donnees et execution de la commande SQL
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Creation d'un objet Membre avec les parametres de la reponse
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
